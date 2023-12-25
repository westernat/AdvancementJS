package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite entity = EntityPredicate.Composite.ANY;

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Composite.wrap(entity);
    }

    public void setEntity(ResourceLocation entityId) {
        this.entity = warpEntity(entityId);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
