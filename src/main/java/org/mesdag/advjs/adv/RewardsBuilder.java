package org.mesdag.advjs.adv;


import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
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

    public AdvancementRewards build() {
        return new AdvancementRewards(experience, loot, recipes, function);
    }
}
