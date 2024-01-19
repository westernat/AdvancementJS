package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    LootContextPredicate parent = LootContextPredicate.EMPTY;
    LootContextPredicate partner = LootContextPredicate.EMPTY;
    LootContextPredicate child = LootContextPredicate.EMPTY;

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
    public void setPartner(ICondition... conditions) {
        this.partner = wrapEntity(conditions);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChildByPredicate(EntityPredicate child) {
        this.child = wrapEntity(child);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChildByType(EntityType<?> child) {
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
