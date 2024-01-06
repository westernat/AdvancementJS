package org.mesdag.advjs.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.world.level.storage.loot.PredicateManager;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.AdvancementEvent;
import org.mesdag.advjs.adv.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

import static org.mesdag.advjs.adv.Data.*;

@Mixin(ServerAdvancementManager.class)
public abstract class ServerAdvancementManagerMixin {
    @Shadow
    @Final
    private PredicateManager predicateManager;

    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementList;add(Ljava/util/Map;)V"))
    private Map<ResourceLocation, Advancement.Builder> advjs$reload(Map<ResourceLocation, Advancement.Builder> map) {
        new AdvancementEvent().post(ScriptType.SERVER, "advjs");
        advJS$remove(map);
        advJS$modify(map, predicateManager);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS Loaded!");
        return map;
    }

    @Unique
    private static void advJS$remove(Map<ResourceLocation, Advancement.Builder> map) {
        int counter = 0;
        for (ResourceLocation remove : REMOVES) {
            if (map.remove(remove) != null) {
                counter++;
            } else {
                AdvJS.LOGGER.warn("Advancement '{}' is not exist", remove);
            }
        }
        AdvJS.LOGGER.info("Removed {} advancements", counter);
    }

    @Unique
    private static void advJS$modify(Map<ResourceLocation, Advancement.Builder> map, PredicateManager predicateManager) {
        int counter = 0;
        for (Map.Entry<ResourceLocation, AdvGetter> entry : GETTER_MAP.entrySet()) {
            ResourceLocation path = entry.getKey();
            Advancement.Builder builder = map.get(path);
            if (builder != null) {
                AdvGetter getter = entry.getValue();
                JsonObject oldJson = builder.serializeToJson();
                ResourceLocation parentId = new ResourceLocation(oldJson.get("parent").getAsString());

                DisplayInfo oldDisplay = DisplayInfo.fromJson(oldJson.get("display").getAsJsonObject());
                DisplayBuilder neoDisplayBuilder = new DisplayBuilder(
                    oldDisplay.getIcon(),
                    oldDisplay.getTitle(),
                    oldDisplay.getDescription(),
                    oldDisplay.getBackground(),
                    oldDisplay.getFrame(),
                    oldDisplay.shouldShowToast(),
                    oldDisplay.shouldAnnounceChat(),
                    oldDisplay.isHidden()
                );
                getter.getDisplayConsumer().accept(neoDisplayBuilder);
                DisplayInfo neoDisplay = neoDisplayBuilder.build();

                JsonElement oldRewardsJson = oldJson.get("rewards");
                AdvancementRewards neoRewards;
                if (oldRewardsJson.isJsonNull()) {
                    neoRewards = AdvancementRewards.EMPTY;
                } else {
                    RewardsBuilder neoRewardsBuilder = advJS$getRewardsBuilder(oldRewardsJson.getAsJsonObject());
                    getter.getRewardsConsumer().accept(neoRewardsBuilder);
                    neoRewards = neoRewardsBuilder.build();
                }

                Map<String, Criterion> oldCriteria = Criterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new DeserializationContext(path, predicateManager));
                CriteriaBuilder neoCriteriaBuilder = new CriteriaBuilder(oldCriteria, oldJson.get("requirements").getAsJsonArray());
                getter.getCriteriaConsumer().accept(neoCriteriaBuilder);
                String[][] neoRequirements = neoCriteriaBuilder.getRequirements();

                Advancement.Builder neo = Advancement.Builder.advancement()
                    .parent(parentId)
                    .display(neoDisplay)
                    .rewards(neoRewards)
                    .requirements(neoRequirements);
                for (Map.Entry<String, Criterion> pair : neoCriteriaBuilder.getCriteria().entrySet()) {
                    neo.addCriterion(pair.getKey(), pair.getValue());
                }
                map.put(path, neo);
                counter++;
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
        ResourceLocation[] loot = new ResourceLocation[lootArray.size()];

        for (int j = 0; j < loot.length; ++j) {
            loot[j] = new ResourceLocation(lootArray.get(j).getAsString());
        }

        JsonArray recipeArray = rewardsJson.get("recipes").getAsJsonArray();
        ResourceLocation[] recipes = new ResourceLocation[recipeArray.size()];

        for (int k = 0; k < recipes.length; ++k) {
            recipes[k] = new ResourceLocation(recipeArray.get(k).getAsString());
        }

        CommandFunction.CacheableFunction function;
        if (rewardsJson.has("function")) {
            function = new CommandFunction.CacheableFunction(new ResourceLocation(rewardsJson.get("function").getAsString()));
        } else {
            function = CommandFunction.CacheableFunction.NONE;
        }

        return new RewardsBuilder(experience, loot, recipes, function);
    }

    @Unique
    private static void advJS$add(Map<ResourceLocation, Advancement.Builder> builderMap) {
        int counter = 0;
        for (Map.Entry<ResourceLocation, AdvBuilder> entry : BUILDER_MAP.entrySet()) {
            AdvBuilder advBuilder = entry.getValue();
            if (advBuilder.isRoot()) {
                builderMap.put(entry.getKey(), advJS$build(advBuilder));
                counter++;
            } else { // advBuilder.parent != null
                ResourceLocation parentId = advBuilder.getParent();
                if (builderMap.containsKey(parentId)) {
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
            advBuilder.getRequirements()
        ).deconstruct();
    }
}
