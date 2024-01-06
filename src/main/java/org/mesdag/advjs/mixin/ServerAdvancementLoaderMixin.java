package org.mesdag.advjs.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.loot.condition.LootConditionManager;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.AdvCreateEvent;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.adv.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.Map;

import static org.mesdag.advjs.adv.Data.*;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {
    @Shadow
    @Final
    private LootConditionManager conditionManager;

    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementManager;load(Ljava/util/Map;)V"))
    private Map<Identifier, Advancement.Builder> advjs$reload(Map<Identifier, Advancement.Builder> map) {
        new AdvCreateEvent().post(ScriptType.SERVER, "advjs");
        advJS$remove(map);
        advJS$modify(map, conditionManager);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS Loaded!");
        return map;
    }

    @Unique
    private static void advJS$remove(Map<Identifier, Advancement.Builder> map) {
        int counter = 0;
        for (Identifier remove : REMOVES) {
            if (map.remove(remove) != null) {
                counter++;
            } else {
                AdvJS.LOGGER.warn("Advancement '{}' is not exist", remove);
            }
        }
        AdvJS.LOGGER.info("Removed {} advancements", counter);
    }

    @Unique
    private static void advJS$modify(Map<Identifier, Advancement.Builder> map, LootConditionManager conditionManager) {
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

                Map<String, AdvancementCriterion> oldCriteria = AdvancementCriterion.criteriaFromJson(oldJson.get("criteria").getAsJsonObject(), new AdvancementEntityPredicateDeserializer(path, conditionManager));
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
        ).createTask();
    }
}
