package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

class ItemDurabilityBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints durability = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints delta = MinMaxBounds.Ints.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }

    public void setDurability(MinMaxBounds.Ints durability) {
        this.durability = durability;
    }

    public void setDelta(MinMaxBounds.Ints delta) {
        this.delta = delta;
    }
}
