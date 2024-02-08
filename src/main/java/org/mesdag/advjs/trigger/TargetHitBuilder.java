package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class TargetHitBuilder extends BaseTriggerInstanceBuilder {
    MinMaxBounds.Ints signalStrength = MinMaxBounds.Ints.ANY;
    ContextAwarePredicate projectile = ContextAwarePredicate.ANY;

    @Info("The redstone signal that will come out of the target block.")
    public void setSignalStrength(Bounds signalStrength) {
        this.signalStrength = signalStrength.toIntegerBounds();
    }

    @Info("The projectile hit the target block.")
    public void setProjectileByPredicate(EntityPredicate projectile) {
        this.projectile = wrapEntity(projectile);
    }

    @Info("The projectile hit the target block.")
    public void setProjectile(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.projectile = wrapEntity(builder.build());
    }

    @Info("The projectile hit the target block.")
    public void setProjectileByType(EntityType<?> projectile) {
        this.projectile = wrapEntity(projectile);
    }

    @Info("The projectile hit the target block.")
    public void setProjectileByCondition(ICondition... conditions) {
        this.projectile = wrapEntity(conditions);
    }
}
