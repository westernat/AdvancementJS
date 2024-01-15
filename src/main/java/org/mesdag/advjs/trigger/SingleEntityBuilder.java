package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    LootContextPredicate entity = LootContextPredicate.EMPTY;

    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = EntityPredicate.asLootContextPredicate(entity);
    }

    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.asLootContextPredicate(builder.build());
    }

    public void setEntityByType(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
