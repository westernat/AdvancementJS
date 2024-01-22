package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class LightningStrikeBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate lightning = ContextAwarePredicate.ANY;
    ContextAwarePredicate bystander = ContextAwarePredicate.ANY;

    @Info("The lightning bolt that disappeared.")
    public void setLightningByPredicate(EntityPredicate lightning) {
        this.lightning = wrapEntity(lightning);
    }

    @Info("The lightning bolt that disappeared.")
    public void setLightning(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.lightning = wrapEntity(builder.build());
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystanderByPredicate(EntityPredicate bystander) {
        this.bystander = wrapEntity(bystander);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.bystander = wrapEntity(builder.build());
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystanderByType(EntityType<?> bystander) {
        this.bystander = wrapEntity(bystander);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystanderByCondition(ICondition... conditions) {
        this.bystander = wrapEntity(conditions);
    }
}
