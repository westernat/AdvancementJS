package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import org.mesdag.advjs.util.ItemSetter;

class ItemUsedOnBlockBuilder extends AbstractTriggerBuilder implements ItemSetter {
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    public void setTool(ItemPredicate item) {
        this.item = item;
    }

    public void setTool(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
