package org.mesdag.advjs.predicate;

import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.MobEffectsPredicate;

import javax.annotation.Nullable;

public class MobEffectInstancePredicateBuilder {
    private MinMaxBounds.Ints amplifier = MinMaxBounds.Ints.ANY;
    private MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;
    @Nullable
    private Boolean ambient = null;
    @Nullable
    private Boolean visible = null;

    public MobEffectInstancePredicateBuilder amplifier(MinMaxBounds.Ints bounds) {
        amplifier = bounds;
        return this;
    }

    public MobEffectInstancePredicateBuilder duration(MinMaxBounds.Ints bounds) {
        duration = bounds;
        return this;
    }

    public MobEffectInstancePredicateBuilder ambient(boolean bool) {
        ambient = bool;
        return this;
    }

    public MobEffectInstancePredicateBuilder visible(boolean bool) {
        visible = bool;
        return this;
    }

    public MobEffectsPredicate.MobEffectInstancePredicate build() {
        return new MobEffectsPredicate.MobEffectInstancePredicate(amplifier, duration, ambient, visible);
    }
}
