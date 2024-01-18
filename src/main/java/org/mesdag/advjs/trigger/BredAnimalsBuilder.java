package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate parent = ContextAwarePredicate.ANY;
    ContextAwarePredicate partner = ContextAwarePredicate.ANY;
    ContextAwarePredicate child = ContextAwarePredicate.ANY;

    @Info("The parent.")
    public void setParentByType(EntityType<?> parent) {
        this.parent = wrapEntity(parent);
    }

    @Info("The parent.")
    public void setParentByPredicate(EntityPredicate parent) {
        this.parent = wrapEntity(parent);
    }

    @Info("The parent.")
    public void setParent(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.parent = wrapEntity(builder.build());
    }

    @Info("The parent.")
    public void setParent(ICondition... conditions) {
        this.parent = wrapEntity(conditions);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartnerByType(EntityType<?> partner) {
        this.partner = wrapEntity(partner);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartnerByPredicate(EntityPredicate partner) {
        this.partner = wrapEntity(partner);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.partner = wrapEntity(builder.build());
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(ICondition... conditions) {
        this.partner = wrapEntity(conditions);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChildByType(EntityType<?> child) {
        this.child = wrapEntity(child);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChildByPredicate(EntityPredicate child) {
        this.child = wrapEntity(child);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.child = wrapEntity(builder.build());
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(ICondition... conditions) {
        this.child = wrapEntity(conditions);
    }
}
