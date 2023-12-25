package org.mesdag.advjs.trigger;

import net.minecraft.resources.ResourceLocation;

class RecipeUnlockedBuilder extends AbstractTriggerBuilder{
    ResourceLocation recipe = new ResourceLocation("wooden_sword");

    public void setRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
    }
}
