package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

class EffectsChangedBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityEffectPredicate effects = EntityEffectPredicate.EMPTY;
    EntityPredicate.Extended source = EntityPredicate.Extended.EMPTY;

    public void setEffects(EntityEffectPredicate effects) {
        this.effects = effects;
    }

    public void setSource(EntityPredicate source) {
        this.source = EntityPredicate.Extended.ofLegacy(source);
    }

    public void setSource(EntityType<?> entityType) {
        this.source = warpEntity(entityType);
    }
}
