package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

class TargetHitBuilder extends AbstractTriggerBuilder implements EntitySetter {
    NumberRange.IntRange signalStrength = NumberRange.IntRange.ANY;
    EntityPredicate.Extended projectile = EntityPredicate.Extended.EMPTY;

    @Info("The redstone signal that will come out of the target block.")
    public void setSignalStrength(NumberRange.IntRange signalStrength) {
        this.signalStrength = signalStrength;
    }

    @Info("The projectile hit the target block.")
    public void setProjectile(EntityPredicate projectile) {
        this.projectile = EntityPredicate.Extended.ofLegacy(projectile);
    }

    @Info("The projectile hit the target block.")
    public void setProjectile(EntityType<?> entityType) {
        this.projectile = warpEntity(entityType);
    }
}
