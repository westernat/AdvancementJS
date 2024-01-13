package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The fishing rod used.")
    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    @Info("The fishing rod used.")
    public void setRod(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.rod = builder.build();
    }

    @Info("The fishing rod used.")
    public void setRod(Ingredient rod) {
        this.item = warpItem(rod);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.wrap(entity);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.wrap(builder.build());
    }

    @Info("The item that was caught.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item that was caught.")
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("The item that was caught.")
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
