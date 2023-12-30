package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

class EffectsChangedBuilder extends AbstractTriggerBuilder implements EntitySetter {
    MobEffectsPredicate effects = MobEffectsPredicate.ANY;
    EntityPredicate.Composite source = EntityPredicate.Composite.ANY;

    public void setEffects(MobEffectsPredicate effects) {
        this.effects = effects;
    }

    public void setSource(EntityPredicate source) {
        this.source = EntityPredicate.Composite.wrap(source);
    }

    public void setSource(EntityType<?> entityType) {
        this.source = warpEntity(entityType);
    }
}
