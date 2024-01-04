package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class ItemUsedOnLocationBuilder extends AbstractTriggerBuilder implements ItemSetter {
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
    public void setTool(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }
}
