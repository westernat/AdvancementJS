package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

class SingleItemBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
