package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class ItemDurabilityBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    NumberRange.IntRange durability = NumberRange.IntRange.ANY;
    NumberRange.IntRange delta = NumberRange.IntRange.ANY;

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }

    @Info("The remaining durability of the item.")
    public void setDurability(NumberRange.IntRange durability) {
        this.durability = durability;
    }

    @Info("The change in durability (negative numbers are used to indicate a decrease in durability).")
    public void setDelta(NumberRange.IntRange delta) {
        this.delta = delta;
    }
}
