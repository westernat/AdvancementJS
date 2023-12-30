package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class ItemDurabilityBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints durability = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints delta = MinMaxBounds.Ints.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    public void setDurability(MinMaxBounds.Ints durability) {
        this.durability = durability;
    }

    public void setDelta(MinMaxBounds.Ints delta) {
        this.delta = delta;
    }
}
