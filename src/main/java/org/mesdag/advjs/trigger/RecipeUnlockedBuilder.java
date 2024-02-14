package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.util.Identifier;

public class RecipeUnlockedBuilder extends BaseTriggerInstanceBuilder {
    Identifier recipe = new Identifier("wooden_sword");

    @Info("The recipe that was unlocked.")
    public void setRecipe(Identifier recipe) {
        this.recipe = recipe;
    }
}
