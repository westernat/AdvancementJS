package org.mesdag.advjs.trigger;

import net.minecraft.util.Identifier;

class RecipeUnlockedBuilder extends AbstractTriggerBuilder{
    Identifier recipe = new Identifier("wooden_sword");

    public void setRecipe(Identifier recipe) {
        this.recipe = recipe;
    }
}
