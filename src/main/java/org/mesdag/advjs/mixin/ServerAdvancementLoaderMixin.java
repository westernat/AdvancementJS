package org.mesdag.advjs.mixin;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.item.Item;
import net.minecraft.loot.LootManager;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.AdvJSPlugin;
import org.mesdag.advjs.configure.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static org.mesdag.advjs.configure.Data.*;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {
    @Shadow
    @Final
    private LootManager conditionManager;

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At("HEAD"))
    private void advJS$remove(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        AdvJS.ADVANCEMENT.post(new AdvConfigureEvent());

        int counter = 0;
        ImmutableSet.Builder<Identifier> builder = new ImmutableSet.Builder<>();
        for (Map.Entry<Identifier, JsonElement> entry : map.entrySet()) {
            Identifier key = entry.getKey();
            if (key.getPath().startsWith("recipe")) {
                // Filter all recipe advancement
                continue;
            }

            JsonObject value = entry.getValue().getAsJsonObject();
            for (RemoveFilter filter : FILTERS) {
                Item item;
                String frame;
                if (value.has("display")) {
                    JsonObject display = value.get("display").getAsJsonObject();
                    item = display.has("icon") ? JsonHelper.getItem(display.get("icon").getAsJsonObject(), "item", null) : null;
                    frame = display.has("frame") ? display.get("frame").getAsString() : "task";
                } else {
                    continue;
                }

                String parent = null;
                if (value.has("parent")) {
                    parent = value.get("parent").getAsString();
                }

                if (filter.matches(key, item, frame, parent)) {
                    builder.add(key);
                }
            }
        }

        for (Identifier remove : builder.build()) {
            map.remove(remove);
            counter++;
        }

        AdvJS.LOGGER.info("Removed {} advancements", counter);
    }

    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementManager;load(Ljava/util/Map;)V"))
    private Map<Identifier, Advancement.Builder> advjs$configure(Map<Identifier, Advancement.Builder> map) {
        advJS$modify(map, conditionManager);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS Loaded!");
        return map;
    }

    @Unique
    private static void advJS$modify(Map<Identifier, Advancement.Builder> map, LootManager predicateManager) {
        if (AdvJSPlugin.DEBUG) {
            AdvJS.LOGGER.debug("Modification details:");
        }

        int counter = 0;
        for (Map.Entry<Identifier, AdvGetter> entry : GETTER_MAP.entrySet()) {
            Identifier path = entry.getKey();
            Advancement.Builder builder = map.get(path);
            if (builder != null) {
                AdvGetter getter = entry.getValue();
                JsonObject oldJson = builder.toJson();
                Identifier parentId = new Identifier(oldJson.get("parent").getAsString());

                AdvancementDisplay oldDisplay = AdvancementDisplay.fromJson(oldJson.get("display").getAsJsonObject());
                DisplayBuilder neoDisplayBuilder = new DisplayBuilder(
                    oldDisplay.getIcon(),
                    oldDisplay.getTitle(),
                    oldDisplay.getDescription(),
                    oldDisplay.getBackground(),
                    oldDisplay.getFrame(),
                    oldDisplay.shouldShowToast(),
                    oldDisplay.shouldAnnounceToChat(),
                    oldDisplay.isHidden()
                );
                getter.getDisplayConsumer().accept(neoDisplayBuilder);
                AdvancementDisplay neoDisplay = neoDisplayBuilder.build();

                JsonElement oldRewardsJson = oldJson.get("rewards");
                AdvancementRewards neoRewards;
                if (oldRewardsJson.isJsonNull()) {
                    neoRewards = AdvancementRewards.NONE;
                } else {
                    RewardsBuilder neoRewardsBuilder = advJS$getRewardsBuilder(oldRewardsJson.getAsJsonObject());
                    getter.getRewardsConsumer().accept(neoRewardsBuilder);
                    neoRewards = neoRewardsBuilder.build();
                }

                Map<String, AdvancementCriterion> oldCriteria = AdvancementCriterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new AdvancementEntityPredicateDeserializer(path, predicateManager));
                CriteriaBuilder neoCriteriaBuilder = new CriteriaBuilder(oldCriteria, oldJson.get("requirements").getAsJsonArray());
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
                map.put(path, neo);
                counter++;

                if (AdvJSPlugin.DEBUG) {
                    AdvJS.LOGGER.debug("""
                            identifier: {}
                                parent: {}
                                display:
                                    icon: {} -> {}
                                    title: {} -> {}
                                    description: {} -> {}
                                    background: {} -> {}
                                    frame: {} -> {}
                                    showToast: {} -> {}
                                    announceToChat: {} -> {}
                                    hidden: {} -> {}
                                rewards: {}
                                requirements: {}
                                criteria: {}
                            """,
                        path,
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
                    );
                }
            } else {
                AdvJS.LOGGER.error("Advancement '{}' is not exist", path);
            }
        }
        AdvJS.LOGGER.info("Modified {} advancements", counter);
    }

    @Unique
    private static RewardsBuilder advJS$getRewardsBuilder(JsonObject rewardsJson) {
        int experience = rewardsJson.get("experience").getAsInt();
        JsonArray lootArray = rewardsJson.get("loot").getAsJsonArray();
        Identifier[] loot = new Identifier[lootArray.size()];

        for (int j = 0; j < loot.length; ++j) {
            loot[j] = new Identifier(lootArray.get(j).getAsString());
        }

        JsonArray recipeArray = rewardsJson.get("recipes").getAsJsonArray();
        Identifier[] recipes = new Identifier[recipeArray.size()];

        for (int k = 0; k < recipes.length; ++k) {
            recipes[k] = new Identifier(recipeArray.get(k).getAsString());
        }

        CommandFunction.LazyContainer function;
        if (rewardsJson.has("function")) {
            function = new CommandFunction.LazyContainer(new Identifier(rewardsJson.get("function").getAsString()));
        } else {
            function = CommandFunction.LazyContainer.EMPTY;
        }

        return new RewardsBuilder(experience, loot, recipes, function);
    }

    @Unique
    private static void advJS$add(Map<Identifier, Advancement.Builder> builderMap) {
        int counter = 0;
        for (Map.Entry<Identifier, AdvBuilder> entry : BUILDER_MAP.entrySet()) {
            AdvBuilder advBuilder = entry.getValue();
            if (advBuilder.isRoot()) {
                builderMap.put(entry.getKey(), advJS$build(advBuilder));
                counter++;
            } else { // advBuilder.parent != null
                Identifier parentId = advBuilder.getParent();
                if (BUILDER_MAP.containsKey(parentId) || builderMap.containsKey(parentId)) {
                    Advancement.Builder builder = advJS$build(advBuilder);
                    builderMap.put(advBuilder.getSavePath(), builder.parent(parentId));
                    counter++;
                } else {
                    AdvJS.LOGGER.error("Advancement '{}' is not exist", parentId);
                }
            }
        }
        AdvJS.LOGGER.info("Added {} advancements", counter);
    }

    @Unique
    private static Advancement.Builder advJS$build(AdvBuilder advBuilder) {
        if (advBuilder.isWarn()) {
            advBuilder.display(builder -> {
                builder.setTitle(ATTENTION);
                builder.setDescription(ATTENTION_DESC);
            });
            AdvJS.LOGGER.warn("A warn advancement created, the parent is '{}'", advBuilder.getParent());
        }
        return new Advancement(
            advBuilder.getSavePath(),
            null,
            advBuilder.getDisplayInfo(),
            advBuilder.getRewards(),
            advBuilder.getCriteria(),
            advBuilder.getRequirements(),
            advBuilder.isSendsTelemetryEvent()
        ).createTask();
    }
}
