package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

public class ItemPickedUpByPlayerBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

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
        this.entity = EntityPredicate.wrap(entity);
    }

    @Info("The entity that threw the item.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.wrap(builder.build());
    }

    @Info("The entity that threw the item.")
    public void setEntityByType(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
