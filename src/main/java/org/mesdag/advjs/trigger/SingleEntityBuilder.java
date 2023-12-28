package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.EntitySetter;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    public void setEntity(Identifier entityId) {
        this.entity = warpEntity(entityId);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
