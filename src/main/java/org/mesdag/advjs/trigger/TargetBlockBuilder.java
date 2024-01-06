package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

class TargetBlockBuilder extends AbstractTriggerBuilder implements EntitySetter {
    MinMaxBounds.Ints signalStrength= MinMaxBounds.Ints.ANY;
    EntityPredicate.Composite projectile= EntityPredicate.Composite.ANY;

    public void setSignalStrength(MinMaxBounds.Ints signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setProjectile(EntityPredicate projectile) {
        this.projectile = EntityPredicate.Composite.wrap(projectile);
    }

    public void setProjectile(EntityType<?> entityType) {
        this.projectile = warpEntity(entityType);
    }
}
