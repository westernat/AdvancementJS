package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    ContextAwarePredicate killed = ContextAwarePredicate.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    @Info("The entity that was killed.")
    public void setKilled(EntityPredicate killed) {
        this.killed = EntityPredicate.wrap(killed);
    }

    @Info("The entity that was killed.")
    public void setKilled(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.killed = EntityPredicate.wrap(builder.build());
    }

    @Info("The entity that was killed.")
    public void setKilled(EntityType<?> killed) {
        this.killed = warpEntity(killed);
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
