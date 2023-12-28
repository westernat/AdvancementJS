package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.predicate.entity.EntityEffectPredicate;

import java.util.Map;

class MobEffectsPredicateBuilder {
    final Map<StatusEffect, EntityEffectPredicate.EffectData> effects = Maps.newLinkedHashMap();

    public void add(StatusEffect mobEffect, EntityEffectPredicate.EffectData mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }
}
