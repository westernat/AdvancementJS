package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class ItemInteractWithBlockBuilder extends AbstractTriggerBuilder implements ItemSetter {
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The location of the block the item was used on.")
    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    @Info("The tool is the item used on the block.")
    public void setTool(ItemPredicate item) {
        this.item = item;
    }

    @Info("The tool is the item used on the block.")
    public void setTool(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
