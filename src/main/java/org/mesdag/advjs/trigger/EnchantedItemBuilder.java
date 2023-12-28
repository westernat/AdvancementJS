package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

class EnchantedItemBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item;
    NumberRange.IntRange levels;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS){
        this.item = warpItem(ingredientJS);
    }

    public void setLevels(NumberRange.IntRange levels) {
        this.levels = levels;
    }
}
