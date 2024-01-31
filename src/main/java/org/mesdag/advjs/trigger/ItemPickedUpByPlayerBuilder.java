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

public class ItemPickedUpByPlayerBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    LootContextPredicate entity = LootContextPredicate.EMPTY;

    @Info("The item thrown.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item thrown.")
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("The item thrown.")
    public void setItem(Ingredient ingredient) {
        this.item = wrapItem(ingredient);
    }

    @Info("The entity that threw the item.")
    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = wrapEntity(entity);
    }

    @Info("The entity that threw the item.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = wrapEntity(builder.build());
    }

    @Info("The entity that threw the item.")
    public void setEntityByType(EntityType<?> entityType) {
        this.entity = wrapEntity(entityType);
    }

    @Info("The entity that threw the item.")
    public void setEntityByCondition(ICondition... conditions) {
        this.entity = wrapEntity(conditions);
    }
}