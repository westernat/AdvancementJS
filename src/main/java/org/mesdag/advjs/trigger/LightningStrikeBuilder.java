package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.function.Consumer;

class LightningStrikeBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate lightning = ContextAwarePredicate.ANY;
    ContextAwarePredicate bystander = ContextAwarePredicate.ANY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.wrap(lightning);
    }

    @Info("The lightning bolt that disappeared.")
    public void setLightning(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.lightning = EntityPredicate.wrap(builder.build());
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.wrap(bystander);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.bystander = EntityPredicate.wrap(builder.build());
    }
}
