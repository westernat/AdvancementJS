package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class SingleItemBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    public void setItem(Ingredient item) {
        this.item = wrapItem(item);
    }
}
