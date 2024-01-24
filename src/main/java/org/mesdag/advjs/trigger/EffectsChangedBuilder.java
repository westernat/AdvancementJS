package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.MobEffectsPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.function.Consumer;

class EffectsChangedBuilder extends AbstractTriggerBuilder {
    MobEffectsPredicate effects = MobEffectsPredicate.ANY;
    ContextAwarePredicate source = ContextAwarePredicate.ANY;

    @Info("A list of active status effects the player currently has.")
    public void setEffectsByPredicate(MobEffectsPredicate effects) {
        this.effects = effects;
    }

    @Info("A list of active status effects the player currently has.")
    public void setEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder = new MobEffectsPredicateBuilder();
        consumer.accept(builder);
        this.effects = builder.build();
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSourceByPredicate(EntityPredicate source) {
        this.source = EntityPredicate.wrap(source);
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSource(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.source = wrapEntity(builder.build());
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSourceByType(EntityType<?> entityType) {
        this.source = wrapEntity(entityType);
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSourceByCondition(ICondition... conditions) {
        this.source = wrapEntity(conditions);
    }
}
