package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.effect.MobEffect;

import java.util.Map;

class MobEffectsPredicateBuilder {
    final Map<MobEffect, MobEffectsPredicate.MobEffectInstancePredicate> effects = Maps.newLinkedHashMap();

    public void add(MobEffect mobEffect, MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }
}
