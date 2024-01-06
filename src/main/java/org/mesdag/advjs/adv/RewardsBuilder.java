package org.mesdag.advjs.adv;

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

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLoot(ResourceLocation... loot) {
        this.loot = loot;
    }

    public void setRecipes(ResourceLocation... recipes) {
        this.recipes = recipes;
    }

    public void setFunction(ResourceLocation functionId) {
        this.function = new CommandFunction.CacheableFunction(functionId);
    }

    public AdvancementRewards build() {
        return new AdvancementRewards(experience, loot, recipes, function);
    }
}
