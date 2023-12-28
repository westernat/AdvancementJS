package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

class ItemUsedOnBlockBuilder extends AbstractTriggerBuilder implements ItemSetter {
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
