package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.predicate.entity.EntityEffectPredicate;

import java.util.Map;
import java.util.function.Consumer;

public class MobEffectsPredicateBuilder {
    final Map<StatusEffect, EntityEffectPredicate.EffectData> effects = Maps.newLinkedHashMap();

    @Info(params = {
        @Param(name = "mobEffect",value = "A status effect that must be present."),
        @Param(name = "mobEffectInstancePredicate", value = "Properties of the status")
    })
    public void addEffectByPredicate(StatusEffect mobEffect, EntityEffectPredicate.EffectData mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }

    @Info(params = {
        @Param(name = "mobEffect", value = "A status effect that must be present."),
        @Param(name = "consumer", value = "Properties of the status")
    })
    public void addEffect(StatusEffect mobEffect, Consumer<MobEffectInstancePredicateBuilder> consumer) {
        MobEffectInstancePredicateBuilder builder = new MobEffectInstancePredicateBuilder();
        consumer.accept(builder);
        effects.put(mobEffect, builder.build());
    }

    @HideFromJS
    public EntityEffectPredicate build() {
        return new EntityEffectPredicate(effects);
    }
}
