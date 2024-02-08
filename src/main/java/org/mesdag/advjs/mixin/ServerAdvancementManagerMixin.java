package org.mesdag.advjs.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.core.ItemKJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootDataManager;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.advancement.*;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.AdvJSEvents;
import org.mesdag.advjs.util.AdvancementFilter;
import org.mesdag.advjs.util.DisplayOffset;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

import static org.mesdag.advjs.util.Data.*;

@Mixin(ServerAdvancementManager.class)
public abstract class ServerAdvancementManagerMixin {
    @Shadow
    @Final
    private LootDataManager lootData;

    @Shadow
    private AdvancementList advancements;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    private void advJS$remove(Map<ResourceLocation, JsonElement> map, ResourceManager resourceManager, ProfilerFiller profilerFiller, CallbackInfo ci) {
        AdvJSEvents.ADVANCEMENT.post(new AdvConfigureEventJS(new Trigger(lootData)));

        int counter = 0;
        ImmutableSet.Builder<ResourceLocation> builder = new ImmutableSet.Builder<>();
        for (Map.Entry<ResourceLocation, JsonElement> entry : map.entrySet()) {
            ResourceLocation key = entry.getKey();
            if (key.toString().startsWith("minecraft:recipe")) continue;

            JsonObject advJson = entry.getValue().getAsJsonObject();
            for (AdvancementFilter filter : FILTERS) {
                if (filter.isResolved()) continue;

                String parent = advJson.has("parent") ? advJson.get("parent").getAsString() : null;
                if (advJson.has("display")) {
                    JsonObject display = advJson.get("display").getAsJsonObject();
                    Item item = display.has("icon") ? GsonHelper.getAsItem(display.get("icon").getAsJsonObject(), "item", null) : null;
                    String frame = display.has("frame") ? display.get("frame").getAsString() : null;
                    if (filter.matches(key, item, frame, parent)) {
                        builder.add(key);
                    }
                } else if (filter.matches(key, null, null, parent)) {
                    builder.add(key);
                }
            }
        }

        for (ResourceLocation remove : builder.build()) {
            map.remove(remove);
            counter++;
        }

        ConsoleJS.SERVER.info("AdvJS: Removed '%s' advancements".formatted(counter));
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementList;add(Ljava/util/Map;)V", shift = At.Shift.BEFORE),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private void advjs$modify_add(Map<ResourceLocation, JsonElement> p_136034_, ResourceManager p_136035_, ProfilerFiller p_136036_, CallbackInfo ci, Map<ResourceLocation, Advancement.Builder> map, AdvancementList advancementlist) {
        advJS$modify(map, lootData);
        advJS$add(map);
        ConsoleJS.SERVER.info("AdvJS: Completely loaded!");
    }

    @Unique
    private static void advJS$modify(Map<ResourceLocation, Advancement.Builder> map, LootDataManager lootData) {
        AdvJS.debugInfo("AdvJS: Modification details:");

        int counter = 0;
        for (Map.Entry<ResourceLocation, AdvGetter> entry : GETTERS.entrySet()) {
            ResourceLocation id = entry.getKey();
            Advancement.Builder builder = map.get(id);
            if (builder == null) {
                ConsoleJS.SERVER.error("AdvJS/modify: Advancement '%s' is not exist".formatted(id));
                continue;
            }
            AdvGetter getter = entry.getValue();
            JsonObject oldJson = builder.serializeToJson();
            ResourceLocation parentId = getter.parent == null ? (oldJson.has("parent") ? new ResourceLocation(oldJson.get("parent").getAsString()) : null) : getter.parent;
            DisplayInfo neoDisplay = null;
            String displayStr = "";

            if (getter.displayConsumer != null) {
                if (oldJson.has("display")) {
                    DisplayInfo oldDisplay = DisplayInfo.fromJson(oldJson.get("display").getAsJsonObject());
                    DisplayBuilder neoDisplayBuilder = new DisplayBuilder(id, oldDisplay);
                    getter.displayConsumer.accept(neoDisplayBuilder);
                    neoDisplay = neoDisplayBuilder.build();

                    displayStr = """
                            display:
                                icon: %s -> %s
                                title: %s -> %s
                                description: %s -> %s
                                background: %s -> %s
                                frame: %s -> %s
                                showToast: %s -> %s
                                announceToChat: %s -> %s
                                hidden: %s -> %s
                        """.formatted(
                        ((ItemKJS) oldDisplay.getIcon().getItem()).kjs$getId(), ((ItemKJS) neoDisplay.getIcon().getItem()).kjs$getId(),
                        oldDisplay.getTitle().getString(), neoDisplay.getTitle().getString(),
                        oldDisplay.getDescription().getString(), neoDisplay.getDescription().getString(),
                        oldDisplay.getBackground(), neoDisplay.getBackground(),
                        oldDisplay.getFrame().getName(), neoDisplay.getFrame().getName(),
                        oldDisplay.shouldShowToast(), neoDisplay.shouldShowToast(),
                        oldDisplay.shouldAnnounceChat(), neoDisplay.shouldAnnounceChat(),
                        oldDisplay.isHidden(), neoDisplay.isHidden()
                    );
                } else {
                    DisplayBuilder neoDisplayBuilder = new DisplayBuilder(id);
                    getter.displayConsumer.accept(neoDisplayBuilder);
                    neoDisplay = neoDisplayBuilder.build();
                }
            }

            JsonElement oldRewardsJson = oldJson.get("rewards");
            AdvancementRewards neoRewards;
            if (oldRewardsJson.isJsonNull()) {
                neoRewards = AdvancementRewards.EMPTY;
            } else {
                RewardsBuilder neoRewardsBuilder = RewardsBuilder.fromJson(oldRewardsJson.getAsJsonObject());
                getter.rewardsConsumer.accept(neoRewardsBuilder);
                neoRewards = neoRewardsBuilder.build();
            }

            Map<String, Criterion> oldCriteria = Criterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new DeserializationContext(id, lootData));
            CriteriaBuilder neoCriteriaBuilder = new CriteriaBuilder(oldCriteria);
            getter.criteriaConsumer.accept(neoCriteriaBuilder);
            String[][] neoRequirements = neoCriteriaBuilder.getRequirements();

            Advancement.Builder neo = Advancement.Builder.advancement().parent(parentId).display(neoDisplay).rewards(neoRewards).requirements(neoRequirements);
            for (Map.Entry<String, Criterion> pair : neoCriteriaBuilder.getCriteria().entrySet()) {
                neo.addCriterion(pair.getKey(), pair.getValue());
            }
            map.put(id, neo);
            counter++;

            AdvJS.debugInfo("""
                identifier: %s
                    parent: %s
                %s
                    rewards: %s
                    requirements: %s
                    criteria: %s
                """
                .formatted(
                    id,
                    parentId,
                    displayStr,
                    neoRewards,
                    neoRequirements,
                    String.join(",", neo.getCriteria().keySet())
                )
            );
        }
        ConsoleJS.SERVER.info("AdvJS: Modified %s advancements".formatted(counter));
    }

    @Unique
    private static void advJS$add(Map<ResourceLocation, Advancement.Builder> map) {
        int counter = 0;
        for (Map.Entry<ResourceLocation, AdvBuilder> entry : BUILDERS.entrySet()) {
            AdvBuilder advBuilder = entry.getValue();
            ResourceLocation parentId = advBuilder.getParent();
            if (parentId == null) {
                map.put(entry.getKey(), advJS$build(advBuilder));
                counter++;
                continue;
            }

            ResourceLocation id = advBuilder.getId();
            if (BUILDERS.containsKey(parentId) || map.containsKey(parentId)) {
                map.put(id, advJS$build(advBuilder).parent(parentId));
                counter++;
            } else {
                map.put(id, advJS$build(advBuilder.setWarn(AdvBuilder.WarnType.NO_PARENT)));
                ConsoleJS.SERVER.error("AdvJS/add: Advancement '%s' can't find parent '%s'".formatted(id, parentId));
            }
        }
        ConsoleJS.SERVER.info("AdvJS: Added %s advancements".formatted(counter));
    }

    @Unique
    private static Advancement.Builder advJS$build(AdvBuilder advBuilder) {
        if (advBuilder.getWarn() != AdvBuilder.WarnType.NONE) {
            advBuilder.display(displayBuilder -> {
                displayBuilder.setTitle(Component.translatable("advjs.attention").withStyle(ChatFormatting.RED));
                displayBuilder.setDescription(advBuilder.getWarn().msg);
                if (advBuilder.getWarn() == AdvBuilder.WarnType.NO_PARENT) {
                    displayBuilder.setBackground(new ResourceLocation("textures/gui/advancements/backgrounds/nether.png"));
                }
            });
            ConsoleJS.SERVER.warn("AdvJS: A warn advancement '%s' created".formatted(advBuilder.getId()));
        }
        return new Advancement(
            advBuilder.getId(),
            null,
            advBuilder.getDisplayInfo(),
            advBuilder.getRewards(),
            advBuilder.getCriteria(),
            advBuilder.getRequirements(),
            advBuilder.isSendsTelemetryEvent()
        ).deconstruct();
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("TAIL"))
    private void advJS$setLocation(Map<ResourceLocation, JsonElement> p_136034_, ResourceManager p_136035_, ProfilerFiller p_136036_, CallbackInfo ci) {
        for (Map.Entry<ResourceLocation, DisplayOffset> entry : DISPLAY_OFFSET.entrySet()) {
            ResourceLocation id = entry.getKey();
            Advancement advancement = advancements.get(id);
            if (advancement == null) {
                ConsoleJS.SERVER.error("AdvJS/displayOffset: Advancement '%s' is not exist".formatted(id));
                continue;
            }

            DisplayOffset offset = entry.getValue();
            advJS$applyOffset(advancement, offset.offsetX(), offset.offsetY(), offset.modifyChildren());
        }
    }

    @Unique
    private static void advJS$applyOffset(Advancement advancement, float x, float y, boolean modifyChildren) {
        DisplayInfo displayInfo = advancement.getDisplay();
        if (displayInfo == null) {
            ConsoleJS.SERVER.error("AdvJS/displayOffset: Advancement '%s' dose not have display".formatted(advancement.getId()));
            return;
        }

        float rawX = displayInfo.getX();
        float neoX = rawX + x;
        float rawY = displayInfo.getY();
        float neoY = rawY + y;
        displayInfo.setLocation(neoX, neoY);
        if (modifyChildren) {
            for (Advancement child : advancement.getChildren()) {
                advJS$applyOffset(child, x, y, true);
            }
        }

        AdvJS.debugInfo("AdvJS: The display location of advancement '%s' has set from (%s, %s) to (%s, %s)".formatted(advancement.getId(), rawX, rawY, neoX, neoY));
    }
}
