package org.mesdag.advjs.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancement.*;
import net.minecraft.item.Item;
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
import org.mesdag.advjs.AdvJSPlugin;
import org.mesdag.advjs.configure.*;
import org.mesdag.advjs.util.AdvRemoveFilter;
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
        AdvJS.ADVANCEMENT.post(new AdvConfigureEvent());

        int counter = 0;
        ImmutableSet.Builder<Identifier> builder = new ImmutableSet.Builder<>();
        for (Map.Entry<Identifier, JsonElement> entry : map.entrySet()) {
            Identifier key = entry.getKey();
            if (key.toString().startsWith("minecraft:recipe")) {
                // Filter all recipe advancement
                continue;
            }

            JsonObject advJson = entry.getValue().getAsJsonObject();
            for (AdvRemoveFilter filter : FILTERS) {
                if (filter.isResolved()) {
                    continue;
                }

                String parent = advJson.has("parent") ? advJson.get("parent").getAsString() : null;
                if (advJson.has("display")) {
                    JsonObject display = advJson.get("display").getAsJsonObject();
                    Item item = display.has("icon") ? JsonHelper.getItem(display.get("icon").getAsJsonObject(), "item", null) : null;
                    String frame = display.has("frame") ? display.get("frame").getAsString() : null;
                    if (filter.matches(key, item, frame, parent)) {
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

        ConsoleJS.SERVER.info("AdvJS: Removed " + counter + " advancements");
    }

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementManager;load(Ljava/util/Map;)V", shift = At.Shift.BEFORE),
        locals = LocalCapture.CAPTURE_FAILSOFT)
    private void advjs$configure(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci, Map<Identifier, Advancement.Builder> map2, AdvancementManager advancementManager) {
        advJS$modify(map2, conditionManager);
        advJS$add(map2);
        ConsoleJS.SERVER.info("AdvJS: Completely loaded!");
    }

    @Unique
    private static void advJS$modify(Map<Identifier, Advancement.Builder> map, LootManager predicateManager) {
        if (AdvJSPlugin.DEBUG) {
            ConsoleJS.SERVER.info("AdvJS: Modification details:");
        }

        int counter = 0;
        for (Map.Entry<Identifier, AdvGetter> entry : GETTER_MAP.entrySet()) {
            Identifier id = entry.getKey();
            Advancement.Builder builder = map.get(id);
            if (builder == null) {
                ConsoleJS.SERVER.error("AdvJS/modify: Advancement '%s' is not exist".formatted(id));
                continue;
            }
            AdvGetter getter = entry.getValue();
            JsonObject oldJson = builder.toJson();
            Identifier parentId = oldJson.has("parent") ? new Identifier(oldJson.get("parent").getAsString()) : null;

            AdvancementDisplay oldDisplay = AdvancementDisplay.fromJson(oldJson.get("display").getAsJsonObject());
            DisplayBuilder neoDisplayBuilder = new DisplayBuilder(id, oldDisplay);
            getter.getDisplayConsumer().accept(neoDisplayBuilder);
            AdvancementDisplay neoDisplay = neoDisplayBuilder.build();

            JsonElement oldRewardsJson = oldJson.get("rewards");
            AdvancementRewards neoRewards;
            if (oldRewardsJson.isJsonNull()) {
                neoRewards = AdvancementRewards.NONE;
            } else {
                RewardsBuilder neoRewardsBuilder = RewardsBuilder.fromJson(oldRewardsJson.getAsJsonObject());
                getter.getRewardsConsumer().accept(neoRewardsBuilder);
                neoRewards = neoRewardsBuilder.build();
            }

            Map<String, AdvancementCriterion> oldCriteria = AdvancementCriterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new AdvancementEntityPredicateDeserializer(id, predicateManager));
            CriteriaBuilder neoCriteriaBuilder = new CriteriaBuilder(oldCriteria);
            getter.getCriteriaConsumer().accept(neoCriteriaBuilder);
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

            if (AdvJSPlugin.DEBUG) {
                ConsoleJS.SERVER.info("""
                    identifier: %s
                        parent: %s
                        display:
                            icon: %s -> %s
                            title: %s -> %s
                            description: %s -> %s
                            background: %s -> %s
                            frame: %s -> %s
                            showToast: %s -> %s
                            announceToChat: %s -> %s
                            hidden: %s -> %s
                        rewards: %s
                        requirements: %s
                        criteria: %s
                    """
                    .formatted(
                        id,
                        parentId,
                        oldDisplay.getIcon().getTranslationKey(), neoDisplay.getIcon().getTranslationKey(),
                        oldDisplay.getTitle().getString(), neoDisplay.getTitle().getString(),
                        oldDisplay.getDescription().getString(), neoDisplay.getDescription().getString(),
                        oldDisplay.getBackground(), neoDisplay.getBackground(),
                        oldDisplay.getFrame().getId(), neoDisplay.getFrame().getId(),
                        oldDisplay.shouldShowToast(), neoDisplay.shouldShowToast(),
                        oldDisplay.shouldAnnounceToChat(), neoDisplay.shouldAnnounceToChat(),
                        oldDisplay.isHidden(), neoDisplay.isHidden(),
                        neoRewards,
                        neoRequirements,
                        String.join(",", neo.getCriteria().keySet())
                    )
                );
            }
        }
        ConsoleJS.SERVER.info("AdvJS: Modified %s advancements".formatted(counter));
    }

    @Unique
    private static void advJS$add(Map<Identifier, Advancement.Builder> map) {
        int counter = 0;
        for (Map.Entry<Identifier, AdvBuilder> entry : BUILDER_MAP.entrySet()) {
            AdvBuilder advBuilder = entry.getValue();
            Identifier parentId = advBuilder.getParent();
            if (parentId == null) {
                map.put(entry.getKey(), advJS$build(advBuilder));
                counter++;
                continue;
            }

            Identifier id = advBuilder.getId();
            if (BUILDER_MAP.containsKey(parentId) || map.containsKey(parentId)) {
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
            });
            ConsoleJS.SERVER.warn("AdvJS: A warn advancement created, the id is '%s'".formatted(advBuilder.getId()));
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
            advJS$applyOffset(advancement, offset.x, offset.y, offset.modifyChildren);
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

        if (AdvJSPlugin.DEBUG) {
            ConsoleJS.SERVER.info("AdvJS: The display location of advancement '%s' has set from (%s, %s) to (%s, %s)"
                .formatted(advancement.getId(), rawX, rawY, neoX, neoY));
        }
    }
}
