package org.mesdag.advjs.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancement.*;
import net.minecraft.loot.LootManager;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
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

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {
    @Shadow
    @Final
    private LootManager conditionManager;

    @Shadow
    private AdvancementManager manager;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void advJS$remove(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        AdvJSEvents.ADVANCEMENT.post(new AdvConfigureEventJS(new Trigger(conditionManager)));

        int counter = 0;
        ImmutableSet.Builder<Identifier> builder = new ImmutableSet.Builder<>();
        for (Map.Entry<Identifier, JsonElement> entry : map.entrySet()) {
            Identifier key = entry.getKey();
            if (key.toString().startsWith("minecraft:recipe")) continue;

            JsonObject advJson = entry.getValue().getAsJsonObject();
            for (AdvancementFilter filter : FILTERS) {
                if (filter.isResolved()) continue;

                Identifier parent = advJson.has("parent") ? new Identifier(advJson.get("parent").getAsString()) : null;
                if (advJson.has("display")) {
                    AdvancementDisplay displayInfo = AdvancementDisplay.fromJson(JsonHelper.getObject(advJson, "display"));
                    if (filter.matches(key, displayInfo.getIcon(), displayInfo.getFrame(), parent)) {
                        builder.add(key);
                    }
                } else if (filter.matches(key, null, null, parent)) {
                    builder.add(key);
                }
            }
        }

        for (Identifier remove : builder.build()) {
            map.remove(remove);
            counter++;
        }

        ConsoleJS.SERVER.info("AdvJS: Removed %s advancements".formatted(counter));
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementManager;load(Ljava/util/Map;)V", shift = At.Shift.BEFORE),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private void advJS$modify_add(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci, Map<Identifier, Advancement.Builder> map2, AdvancementManager advancementManager) {
        advJS$modify(map2, conditionManager);
        advJS$add(map2);
        ConsoleJS.SERVER.info("AdvJS: Completely loaded!");
    }

    @Unique
    private static void advJS$modify(Map<Identifier, Advancement.Builder> map, LootManager predicateManager) {
        AdvJS.debugInfo("AdvJS: Modification details:");

        int counter = 0;
        for (Map.Entry<Identifier, AdvGetter> entry : GETTERS.entrySet()) {
            Identifier id = entry.getKey();
            Advancement.Builder builder = map.get(id);
            if (builder == null) {
                ConsoleJS.SERVER.error("AdvJS/modify: Advancement '%s' is not exist".formatted(id));
                continue;
            }
            AdvGetter getter = entry.getValue();
            JsonObject oldJson = builder.toJson();
            Identifier parentId = oldJson.has("parent") ? new Identifier(oldJson.get("parent").getAsString()) : null;
            AdvancementDisplay neoDisplay = null;
            String displayStr = "";

            if (getter.displayConsumer != null) {
                if (oldJson.has("display")) {
                    AdvancementDisplay oldDisplay = AdvancementDisplay.fromJson(oldJson.get("display").getAsJsonObject());
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
                        oldDisplay.getIcon().getItem().kjs$getId(), neoDisplay.getIcon().getItem().kjs$getId(),
                        oldDisplay.getTitle().getString(), neoDisplay.getTitle().getString(),
                        oldDisplay.getDescription().getString(), neoDisplay.getDescription().getString(),
                        oldDisplay.getBackground(), neoDisplay.getBackground(),
                        oldDisplay.getFrame().getId(), neoDisplay.getFrame().getId(),
                        oldDisplay.shouldShowToast(), neoDisplay.shouldShowToast(),
                        oldDisplay.shouldAnnounceToChat(), neoDisplay.shouldAnnounceToChat(),
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
                neoRewards = AdvancementRewards.NONE;
            } else {
                RewardsBuilder neoRewardsBuilder = RewardsBuilder.fromJson(oldRewardsJson.getAsJsonObject());
                getter.rewardsConsumer.accept(neoRewardsBuilder);
                neoRewards = neoRewardsBuilder.build();
            }

            Map<String, AdvancementCriterion> oldCriteria = AdvancementCriterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new AdvancementEntityPredicateDeserializer(id, predicateManager));
            CriteriaBuilder neoCriteriaBuilder = new CriteriaBuilder(oldCriteria);
            getter.criteriaConsumer.accept(neoCriteriaBuilder);
            String[][] neoRequirements = neoCriteriaBuilder.getRequirements();

            Advancement.Builder neo = Advancement.Builder.create()
                .parent(parentId)
                .display(neoDisplay)
                .rewards(neoRewards)
                .requirements(neoRequirements);
            for (Map.Entry<String, AdvancementCriterion> pair : neoCriteriaBuilder.getCriteria().entrySet()) {
                neo.criterion(pair.getKey(), pair.getValue());
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
    private static void advJS$add(Map<Identifier, Advancement.Builder> map) {
        int counter = 0;
        for (Map.Entry<Identifier, AdvBuilder> entry : BUILDERS.entrySet()) {
            AdvBuilder advBuilder = entry.getValue();
            Identifier parentId = advBuilder.getParent();
            if (parentId == null) {
                map.put(entry.getKey(), advJS$build(advBuilder));
                counter++;
                continue;
            }

            Identifier id = advBuilder.getId();
            if (BUILDERS.containsKey(parentId) || map.containsKey(parentId)) {
                Advancement.Builder builder = advJS$build(advBuilder);
                map.put(id, builder.parent(parentId));
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
                displayBuilder.setTitle(Text.translatable("advjs.attention").formatted(Formatting.RED));
                displayBuilder.setDescription(advBuilder.getWarn().msg);
                if (advBuilder.getWarn() == AdvBuilder.WarnType.NO_PARENT) {
                    displayBuilder.setBackground(new Identifier("textures/gui/advancements/backgrounds/nether.png"));
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
        ).createTask();
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("TAIL"))
    private void advJS$setLocation(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        for (Map.Entry<Identifier, DisplayOffset> entry : DISPLAY_OFFSET.entrySet()) {
            Identifier id = entry.getKey();
            Advancement advancement = manager.get(id);
            if (advancement == null) {
                ConsoleJS.SERVER.warn("AdvJS/displayOffset: Advancement '%s' is not exist".formatted(id));
                continue;
            }

            DisplayOffset offset = entry.getValue();
            advJS$applyOffset(advancement, offset.offsetX(), offset.offsetY(), offset.modifyChildren());
        }
    }

    @Unique
    private static void advJS$applyOffset(Advancement advancement, float x, float y, boolean modifyChildren) {
        AdvancementDisplay displayInfo = advancement.getDisplay();
        if (displayInfo == null) {
            ConsoleJS.SERVER.error("AdvJS/displayOffset: Advancement '%s' dose not have display".formatted(advancement.getId()));
            return;
        }

        float rawX = displayInfo.getX();
        float neoX = rawX + x;
        float rawY = displayInfo.getY();
        float neoY = rawY + y;
        displayInfo.setPos(neoX, neoY);
        if (modifyChildren) {
            for (Advancement child : advancement.getChildren()) {
                advJS$applyOffset(child, x, y, true);
            }
        }

        AdvJS.debugInfo("AdvJS: The display location of advancement '%s' has set from (%s, %s) to (%s, %s)".formatted(advancement.getId(), rawX, rawY, neoX, neoY));
    }
}
