package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

class ItemDurabilityBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    NumberRange.IntRange durability = NumberRange.IntRange.ANY;
    NumberRange.IntRange delta = NumberRange.IntRange.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }

    public void setDurability(NumberRange.IntRange durability) {
        this.durability = durability;
    }

    public void setDelta(NumberRange.IntRange delta) {
        this.delta = delta;
    }
}
