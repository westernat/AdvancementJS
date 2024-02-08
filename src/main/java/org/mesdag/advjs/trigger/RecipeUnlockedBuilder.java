package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;

public class RecipeUnlockedBuilder extends BaseTriggerInstanceBuilder {
    ResourceLocation recipe = new ResourceLocation("wooden_sword");

    @Info("The recipe that was unlocked.")
    public void setRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
    }
}
