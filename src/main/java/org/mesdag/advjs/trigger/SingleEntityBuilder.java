package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

public class SingleEntityBuilder extends BaseTriggerInstanceBuilder {
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = wrapEntity(entity);
    }

    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = wrapEntity(builder.build());
    }

    public void setEntityByType(EntityType<?> entity) {
        this.entity = wrapEntity(entity);
    }

    public void setEntityByCondition(ICondition... conditions) {
        this.entity = wrapEntity(conditions);
    }
}
