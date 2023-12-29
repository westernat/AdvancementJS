package org.mesdag.advjs.getter;


import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;

public class RewardsBuilder {
    private int experience = 0;
    private Identifier[] loot = new Identifier[0];
    private Identifier[] recipes = new Identifier[0];

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLoot(Identifier... loot) {
        this.loot = loot;
    }

    public void setRecipes(Identifier... recipes) {
        this.recipes = recipes;
    }

    public AdvancementRewards getRewards(){
        return new AdvancementRewards(experience, loot, recipes, CommandFunction.LazyContainer.EMPTY);
    }
}
