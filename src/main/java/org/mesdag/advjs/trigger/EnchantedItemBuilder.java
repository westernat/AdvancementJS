package org.mesdag.advjs.trigger;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class EnchantedItemBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item;
    NumberRange.IntRange levels;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    public void setLevels(NumberRange.IntRange levels) {
        this.levels = levels;
    }
}
