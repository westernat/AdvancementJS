package org.mesdag.advjs.advancement;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import org.mesdag.advjs.util.RewardsAccessor;

import java.util.ArrayList;

public class RewardsBuilder {
    private int experience;
    private ResourceLocation[] loot;
    private ResourceLocation[] recipes;
    private CommandFunction.CacheableFunction function;
    private final ArrayList<MobEffectInstance> mobEffectInstances = new ArrayList<>();

    RewardsBuilder() {
        this.experience = 0;
        this.loot = new ResourceLocation[0];
        this.recipes = new ResourceLocation[0];
        this.function = CommandFunction.CacheableFunction.NONE;
    }

    RewardsBuilder(int experience, ResourceLocation[] loot, ResourceLocation[] recipes, CommandFunction.CacheableFunction function) {
        this.experience = experience;
        this.loot = loot;
        this.recipes = recipes;
        this.function = function;
    }

    @HideFromJS
    public static RewardsBuilder fromJson(JsonObject rewardsJson) {
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

    @Info("To give an amount of experience. Defaults to 0.")
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Info(value = "To give items from loot tables to the player.",
        params = @Param(name = "loot", value = "The resource locations of loot table."))
    public void setLoot(ResourceLocation... loot) {
        this.loot = loot;
    }

    @Info(value = "To unlock recipes.",
        params = @Param(name = "recipes", value = "The resource locations of recipe."))
    public void setRecipes(ResourceLocation... recipes) {
        this.recipes = recipes;
    }

    @Info("To run a function. Function tags are not allowed.")
    public void setFunction(ResourceLocation functionId) {
        this.function = new CommandFunction.CacheableFunction(functionId);
    }

    @Info(value = "To give effect.",
        params = {
            @Param(name = "mobEffect"),
            @Param(name = "duration")
        })
    public void addEffect(MobEffect mobEffect, int duration) {
        this.mobEffectInstances.add(new MobEffectInstance(mobEffect, duration));
    }

    @Info(value = "To give effect.",
        params = {
            @Param(name = "mobEffect"),
            @Param(name = "duration"),
            @Param(name = "amplifier")
        })
    public void addEffect(MobEffect mobEffect, int duration, int amplifier) {
        this.mobEffectInstances.add(new MobEffectInstance(mobEffect, duration, amplifier));
    }

    @Info(value = "To give effect.",
        params = {
            @Param(name = "mobEffect"),
            @Param(name = "duration"),
            @Param(name = "amplifier"),
            @Param(name = "ambient"),
            @Param(name = "visible"),
            @Param(name = "showIcon")
        })
    public void addEffect(MobEffect mobEffect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        this.mobEffectInstances.add(new MobEffectInstance(mobEffect, duration, amplifier, ambient, visible, showIcon));
    }

    @HideFromJS
    public AdvancementRewards build() {
        RewardsAccessor accessor = (RewardsAccessor) new AdvancementRewards(experience, loot, recipes, function);
        accessor.advJS$setMobEffectInstances(mobEffectInstances.toArray(MobEffectInstance[]::new));
        return accessor.advJS$getSelf();
    }
}
