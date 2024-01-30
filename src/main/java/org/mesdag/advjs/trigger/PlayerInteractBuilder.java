package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class PlayerInteractBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    ItemPredicate item = ItemPredicate.ANY;
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

    @Info("The item which was in the player's hand during interaction.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item which was in the player's hand during interaction.")
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("The item which was in the player's hand during interaction.")
    public void setItem(Ingredient ingredient) {
        this.item = wrapItem(ingredient);
    }

    @Info("The entity which was interacted with.")
    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = wrapEntity(entity);
    }

    @Info("The entity which was interacted with.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = wrapEntity(builder.build());
    }

    @Info("The entity which was interacted with.")
    public void setEntityByType(EntityType<?> entityType) {
        this.entity = wrapEntity(entityType);
    }

    @Info("The entity which was interacted with.")
    public void setEntityByCondition(ICondition... conditions) {
        this.entity = wrapEntity(conditions);
    }
}
