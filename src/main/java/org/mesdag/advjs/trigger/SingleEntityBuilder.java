package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.wrap(entity);
    }

    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.wrap(builder.build());
    }

    public void setEntity(EntityType<?> entity) {
        this.entity = warpEntity(entity);
    }
}
