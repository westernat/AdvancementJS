package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class ItemDurabilityBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints durability = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints delta = MinMaxBounds.Ints.ANY;

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    @Info("The remaining durability of the item.")
    public void setDurability(MinMaxBounds.Ints durability) {
        this.durability = durability;
    }

    @Info("The change in durability (negative numbers are used to indicate a decrease in durability).")
    public void setDelta(MinMaxBounds.Ints delta) {
        this.delta = delta;
    }
}
