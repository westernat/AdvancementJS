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

public class EntityKillPlayerBuilder extends BaseTriggerInstanceBuilder {
    ContextAwarePredicate killer = ContextAwarePredicate.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
                
        (for example: The skeleton that shot the arrow)
        """)
    public void setKillerByPredicate(EntityPredicate killer) {
        this.killer = wrapEntity(killer);
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
                
        (for example: The skeleton that shot the arrow)
        """)
    public void setKiller(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.killer = wrapEntity(builder.build());
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
                
        (for example: The skeleton that shot the arrow)
        """)
    public void setKillerByType(EntityType<?> killer) {
        this.killer = wrapEntity(killer);
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
                
        (for example: The skeleton that shot the arrow)
        """)
    public void setKillerByCondition(ICondition... conditions) {
        this.killer = wrapEntity(conditions);
    }

    @Info("Checks the type of damage that killed the player.")
    public void setKillingBlowByPredicate(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }

    @Info("Checks the type of damage that killed the player.")
    public void setKillingBlow(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        this.killingBlow = builder.build();
    }
}
