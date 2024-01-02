package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.effect.MobEffect;

import java.util.Map;

class MobEffectsPredicateBuilder {
    final Map<MobEffect, MobEffectsPredicate.MobEffectInstancePredicate> effects = Maps.newLinkedHashMap();

    @Info(params = {
        @Param(name = "mobEffect",value = "A status effect that must be present."),
        @Param(name = "mobEffectInstancePredicate", value = "Properties of the status")
    })
    public void add(MobEffect mobEffect, MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }
}
