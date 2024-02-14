package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

public class SingleEntityBuilder extends BaseTriggerInstanceBuilder {
    LootContextPredicate entity = LootContextPredicate.EMPTY;

    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = wrapEntity(entity);
    }

    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = wrapEntity(builder.build());
    }

    public void setEntityByType(EntityType<?> entityType) {
        this.entity = wrapEntity(entityType);
    }

    public void setEntityByCondition(ICondition... conditions) {
        this.entity = wrapEntity(conditions);
    }
}
