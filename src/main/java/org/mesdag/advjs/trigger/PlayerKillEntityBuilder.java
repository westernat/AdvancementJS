package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder {
    LootContextPredicate killed = LootContextPredicate.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

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
    public void setKilledByCondition(ICondition... conditions) {
        this.killed = wrapEntity(conditions);
    }

    @Info("The type of damage that killed an entity.")
    public void setKillingBlowByPredicate(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }

    @Info("The type of damage that killed an entity.")
    public void setKillingBlow(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        this.killingBlow = builder.build();
    }
}
