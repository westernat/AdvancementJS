package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

class TargetBlockBuilder extends AbstractTriggerBuilder implements EntitySetter {
    NumberRange.IntRange signalStrength;
    EntityPredicate.Extended projectile;

    public void setSignalStrength(NumberRange.IntRange signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setProjectile(EntityPredicate projectile) {
        this.projectile = EntityPredicate.Extended.ofLegacy(projectile);
    }

    public void setProjectile(EntityType<?> entityType) {
        this.projectile = warpEntity(entityType);
    }
}
