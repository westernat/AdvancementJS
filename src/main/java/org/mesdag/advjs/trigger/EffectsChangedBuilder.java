package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

class EffectsChangedBuilder extends AbstractTriggerBuilder implements EntitySetter {
    MobEffectsPredicate effects = MobEffectsPredicate.ANY;
    EntityPredicate.Composite source = EntityPredicate.Composite.ANY;

    @Info("A list of active status effects the player currently has.")
    public void setEffects(MobEffectsPredicate effects) {
        this.effects = effects;
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSource(EntityPredicate source) {
        this.source = EntityPredicate.Composite.wrap(source);
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSource(EntityType<?> entityType) {
        this.source = warpEntity(entityType);
    }
}
