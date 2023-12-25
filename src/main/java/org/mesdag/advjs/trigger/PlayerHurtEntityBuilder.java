package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

class PlayerHurtEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    DamagePredicate damage;
    EntityPredicate.Composite entity;

    public void setDamage(DamagePredicate damage) {
        this.damage = damage;
    }

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
