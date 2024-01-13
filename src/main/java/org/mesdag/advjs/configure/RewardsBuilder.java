package org.mesdag.advjs.configure;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.RewardsAccessor;

import java.util.ArrayList;

public class RewardsBuilder {
    private int experience;
    private Identifier[] loot;
    private Identifier[] recipes;
    private CommandFunction.LazyContainer function;
    private final ArrayList<StatusEffectInstance> mobEffectInstances = new ArrayList<>();

    RewardsBuilder() {
        this.experience = 0;
        this.loot = new Identifier[0];
        this.recipes = new Identifier[0];
        this.function = CommandFunction.LazyContainer.EMPTY;
    }

    RewardsBuilder(int experience, Identifier[] loot, Identifier[] recipes, CommandFunction.LazyContainer function) {
        this.experience = experience;
        this.loot = loot;
        this.recipes = recipes;
        this.function = function;
    }

    @HideFromJS
    public static RewardsBuilder fromJson(JsonObject rewardsJson) {
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

    @Info("To give an amount of experience. Defaults to 0.")
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Info(value = "To give items from loot tables to the player.",
        params = @Param(name = "loot", value = "The resource locations of loot table."))
    public void setLoot(Identifier... loot) {
        this.loot = loot;
    }

    @Info(value = "To unlock recipes.",
        params = @Param(name = "recipes", value = "The resource locations of recipe."))
    public void setRecipes(Identifier... recipes) {
        this.recipes = recipes;
    }

    @Info("To run a function. Function tags are not allowed.")
    public void setFunction(Identifier functionId) {
        this.function = new CommandFunction.LazyContainer(functionId);
    }

    @Info(value = "To give effect.",
        params = {
            @Param(name = "mobEffect"),
            @Param(name = "duration")
        })
    public void addEffect(StatusEffect mobEffect, int duration) {
        this.mobEffectInstances.add(new StatusEffectInstance(mobEffect, duration));
    }

    @Info(value = "To give effect.",
        params = {
            @Param(name = "mobEffect"),
            @Param(name = "duration"),
            @Param(name = "amplifier")
        })
    public void addEffect(StatusEffect mobEffect, int duration, int amplifier) {
        this.mobEffectInstances.add(new StatusEffectInstance(mobEffect, duration, amplifier));
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
    public void addEffect(StatusEffect mobEffect, int duration, int amplifier, boolean ambient, boolean visible, boolean showIcon) {
        this.mobEffectInstances.add(new StatusEffectInstance(mobEffect, duration, amplifier, ambient, visible, showIcon));
    }

    @HideFromJS
    public AdvancementRewards build() {
        RewardsAccessor accessor = (RewardsAccessor) new AdvancementRewards(experience, loot, recipes, function);
        accessor.advJS$setMobEffectInstances(mobEffectInstances.toArray(StatusEffectInstance[]::new));
        return accessor.advJS$getSelf();
    }
}
