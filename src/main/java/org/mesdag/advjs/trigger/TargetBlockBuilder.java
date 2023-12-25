package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

class TargetBlockBuilder extends AbstractTriggerBuilder implements EntitySetter {
    MinMaxBounds.Ints signalStrength;
    EntityPredicate.Composite projectile;

    public void setSignalStrength(MinMaxBounds.Ints signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setProjectile(EntityPredicate projectile) {
        this.projectile = EntityPredicate.Composite.wrap(projectile);
    }

    public void setProjectile(ResourceLocation entityId) {
        this.projectile = warpEntity(entityId);
    }

    public void setProjectile(EntityType<?> entityType) {
        this.projectile = warpEntity(entityType);
    }
}
