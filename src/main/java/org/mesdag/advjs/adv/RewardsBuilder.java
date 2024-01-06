package org.mesdag.advjs.adv;


import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.server.function.CommandFunction;
import net.minecraft.util.Identifier;

public class RewardsBuilder {
    private int experience;
    private Identifier[] loot;
    private Identifier[] recipes;
    private CommandFunction.LazyContainer function;

    RewardsBuilder() {
        this.experience = 0;
        this.loot = new Identifier[0];
        this.recipes = new Identifier[0];
        this.function = CommandFunction.LazyContainer.EMPTY;
    }

    public RewardsBuilder(int experience, Identifier[] loot, Identifier[] recipes, CommandFunction.LazyContainer function) {
        this.experience = experience;
        this.loot = loot;
        this.recipes = recipes;
        this.function = function;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setLoot(Identifier... loot) {
        this.loot = loot;
    }

    public void setRecipes(Identifier... recipes) {
        this.recipes = recipes;
    }

    public void setFunction(Identifier functionId) {
        this.function = new CommandFunction.LazyContainer(functionId);
    }

    public AdvancementRewards build() {
        return new AdvancementRewards(experience, loot, recipes, function);
    }
}
