package org.mesdag.advjs.mixin;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.world.level.storage.loot.PredicateManager;
import org.mesdag.advjs.AdvCreateEvent;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.adv.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.HashMap;
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
        AdvJS.ADVANCEMENT.post(new AdvCreateEvent());
        advJS$remove(map);
        advJS$modify(map, predicateManager);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS loaded!");
        return map;
    }

    @Unique
    private static void advJS$remove(Map<ResourceLocation, Advancement.Builder> map) {
        for (ResourceLocation remove : REMOVES) {
            map.remove(remove);
        }
    }

    @Unique
    private static void advJS$modify(Map<ResourceLocation, Advancement.Builder> map, PredicateManager predicateManager) {
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

                Advancement.Builder neo = Advancement.Builder.advancement()
                    .parent(parentId)
                    .display(neoDisplayBuilder.build())
                    .rewards(neoRewards)
                    .requirements(neoCriteriaBuilder.getRequirements());
                for (Map.Entry<String, Criterion> pair : neoCriteriaBuilder.getCriteria().entrySet()) {
                    neo.addCriterion(pair.getKey(), pair.getValue());
                }
                map.put(path, neo);
            }
        }
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
        HashMap<ResourceLocation, Advancement> advMap = new HashMap<>();
        ArrayList<AdvBuilder> remains = new ArrayList<>();
        for (Map.Entry<ResourceLocation, AdvBuilder> entry : BUILDER_MAP.entrySet()) {
            AdvBuilder builder = entry.getValue();
            if (builder.isRoot()) {
                advMap.put(entry.getKey(), advJS$buildAdv(builder, null));
            } else if (!advJS$buildAndPut(advMap, builder)) {
                remains.add(builder);
            }
        }

        if (remains.size() > 0) {
            for (AdvBuilder builder : remains) {
                advJS$buildAndPut(advMap, builder);
            }
        }

        for (Map.Entry<ResourceLocation, Advancement> entry : advMap.entrySet()) {
            builderMap.put(entry.getKey(), entry.getValue().deconstruct());
        }
    }

    @Unique
    private static boolean advJS$buildAndPut(HashMap<ResourceLocation, Advancement> advMap, AdvBuilder builder) {
        ResourceLocation location = builder.getParent();
        if (advMap.containsKey(location)) {
            Advancement advancement = advJS$buildAdv(builder, advMap.get(location));
            advMap.get(location).addChild(advancement);
            advMap.put(builder.getSavePath(), advancement);
            return true;
        }
        return false;
    }

    @Unique
    private static Advancement advJS$buildAdv(AdvBuilder getter, Advancement parent) {
        if (getter.isAttention()) {
            getter.display(builder -> {
                builder.setTitle(ATTENTION);
                builder.setDescription(ATTENTION_DESC);
            });
        }
        return new Advancement(
            getter.getSavePath(),
            parent,
            getter.getDisplayInfo(),
            getter.getRewards(),
            getter.getCriteria(),
            getter.getRequirements()
        );
    }
}
