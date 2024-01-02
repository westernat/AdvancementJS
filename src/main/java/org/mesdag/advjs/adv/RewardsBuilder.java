package org.mesdag.advjs.adv;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.commands.CommandFunction;
import net.minecraft.resources.ResourceLocation;

public class RewardsBuilder {
    private int experience = 0;
    private ResourceLocation[] loot = new ResourceLocation[0];
    private ResourceLocation[] recipes = new ResourceLocation[0];

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLoot(ResourceLocation... loot) {
        this.loot = loot;
    }

    public void setRecipes(ResourceLocation... recipes) {
        this.recipes = recipes;
    }

    AdvancementRewards build() {
        return new AdvancementRewards(experience, loot, recipes, CommandFunction.CacheableFunction.NONE);
    }
}
