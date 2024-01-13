package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.function.Consumer;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate parent = ContextAwarePredicate.ANY;
    ContextAwarePredicate partner = ContextAwarePredicate.ANY;
    ContextAwarePredicate child = ContextAwarePredicate.ANY;

    @Info("The parent.")
    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.wrap(parent);
    }

    @Info("The parent.")
    public void setParent(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.parent = EntityPredicate.wrap(builder.build());
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.wrap(partner);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.partner = EntityPredicate.wrap(builder.build());
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.wrap(child);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.child = EntityPredicate.wrap(builder.build());
    }
}
