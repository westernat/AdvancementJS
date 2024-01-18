package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate killed = ContextAwarePredicate.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    @Info("The entity that was killed.")
    public void setKilledByPredicate(EntityPredicate killed) {
        this.killed = wrapEntity(killed);
    }

    @Info("The entity that was killed.")
    public void setKilled(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.killed = wrapEntity(builder.build());
    }

    @Info("The entity that was killed.")
    public void setKilledByType(EntityType<?> killed) {
        this.killed = wrapEntity(killed);
    }

    @Info("The entity that was killed.")
    public void setKilled(ICondition... conditions) {
        this.killed = wrapEntity(conditions);
    }

    @Info("The type of damage that killed an entity.")
    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }

    @Info("The type of damage that killed an entity.")
    public void setKillingBlow(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        this.killingBlow = builder.build();
    }
}
