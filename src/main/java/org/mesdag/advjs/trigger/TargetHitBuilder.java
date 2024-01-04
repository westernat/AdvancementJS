package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

class TargetHitBuilder extends AbstractTriggerBuilder implements EntitySetter {
    MinMaxBounds.Ints signalStrength = MinMaxBounds.Ints.ANY;
    ContextAwarePredicate projectile = ContextAwarePredicate.ANY;

    @Info("The redstone signal that will come out of the target block.")
    public void setSignalStrength(MinMaxBounds.Ints signalStrength) {
        this.signalStrength = signalStrength;
    }

    @Info("The projectile hit the target block.")
    public void setProjectile(EntityPredicate projectile) {
        this.projectile = EntityPredicate.wrap(projectile);
    }

    @Info("The projectile hit the target block.")
    public void setProjectile(EntityType<?> projectile) {
        this.projectile = warpEntity(projectile);
    }
}
