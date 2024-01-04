package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.predicate.entity.EntityEffectPredicate;

import java.util.Map;

class MobEffectsPredicateBuilder {
    final Map<StatusEffect, EntityEffectPredicate.EffectData> effects = Maps.newLinkedHashMap();

    @Info(params = {
        @Param(name = "mobEffect",value = "A status effect that must be present."),
        @Param(name = "mobEffectInstancePredicate", value = "Properties of the status")
    })
    public void add(StatusEffect mobEffect, EntityEffectPredicate.EffectData mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }
}
