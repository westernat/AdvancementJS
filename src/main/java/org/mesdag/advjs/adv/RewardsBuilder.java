package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;

public class RewardsBuilder {
    private int experience;
    private ResourceLocation[] loot;
    private ResourceLocation[] recipes;
    private CommandFunction.CacheableFunction function;

    RewardsBuilder() {
        this.experience = 0;
        this.loot = new ResourceLocation[0];
        this.recipes = new ResourceLocation[0];
        this.function = CommandFunction.CacheableFunction.NONE;
    }

    public RewardsBuilder(int experience, ResourceLocation[] loot, ResourceLocation[] recipes, CommandFunction.CacheableFunction function) {
        this.experience = experience;
        this.loot = loot;
        this.recipes = recipes;
        this.function = function;
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
        params = @Param("The resource locations of recipe."))
    public void setRecipes(ResourceLocation... recipes) {
        this.recipes = recipes;
    }

    @Info("To run a function. Function tags are not allowed.")
    public void setFunction(ResourceLocation functionId) {
        this.function = new CommandFunction.CacheableFunction(functionId);
    }

    public AdvancementRewards build() {
        return new AdvancementRewards(experience, loot, recipes, CommandFunction.CacheableFunction.NONE);
    }
}
