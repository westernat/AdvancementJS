package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.util.EntitySetter;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    LootContextPredicate entity = LootContextPredicate.EMPTY;

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.asLootContextPredicate(entity);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
