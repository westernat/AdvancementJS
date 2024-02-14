package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

public class ItemDurabilityBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    NumberRange.IntRange durability = NumberRange.IntRange.ANY;
    NumberRange.IntRange delta = NumberRange.IntRange.ANY;

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("The item before it was damaged, allows you to check the durability before the item was damaged.")
    public void setItem(Ingredient ingredient) {
        this.item = wrapItem(ingredient);
    }

    @Info("The remaining durability of the item.")
    public void setDurability(Bounds durability) {
        this.durability = durability.toIntegerBounds();
    }

    @Info("The change in durability (negative numbers are used to indicate a decrease in durability).")
    public void setDelta(Bounds delta) {
        this.delta = delta.toIntegerBounds();
    }
}
