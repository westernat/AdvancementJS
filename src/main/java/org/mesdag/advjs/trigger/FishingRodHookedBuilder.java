package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    LootContextPredicate entity = LootContextPredicate.EMPTY;
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
    public void setRod(Ingredient ingredient) {
        this.rod = wrapItem(ingredient);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = wrapEntity(entity);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntityByType(EntityType<?> entity) {
        this.entity = wrapEntity(entity);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntityByCondition(ICondition... conditions) {
        this.entity = wrapEntity(conditions);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = wrapEntity(builder.build());
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
        this.item = wrapItem(ingredient);
    }
}
