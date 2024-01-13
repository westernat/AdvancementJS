package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.world.effect.MobEffect;

import java.util.Map;
import java.util.function.Consumer;

public class MobEffectsPredicateBuilder {
    final Map<MobEffect, MobEffectsPredicate.MobEffectInstancePredicate> effects = Maps.newLinkedHashMap();

    @Info(params = {
        @Param(name = "mobEffect", value = "A status effect that must be present."),
        @Param(name = "mobEffectInstancePredicate", value = "Properties of the status")
    })
    public void add(MobEffect mobEffect, MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstancePredicate) {
        effects.put(mobEffect, mobEffectInstancePredicate);
    }

    @Info(params = {
        @Param(name = "mobEffect", value = "A status effect that must be present."),
        @Param(name = "consumer", value = "Properties of the status")
    })
    public void add(MobEffect mobEffect, Consumer<MobEffectInstancePredicateBuilder> consumer) {
        MobEffectInstancePredicateBuilder builder = new MobEffectInstancePredicateBuilder();
        consumer.accept(builder);
        effects.put(mobEffect, builder.build());
    }

    @HideFromJS
    public MobEffectsPredicate build() {
        return new MobEffectsPredicate(effects);
    }
}
