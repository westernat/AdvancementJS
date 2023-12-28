package org.mesdag.advjs.predicate;

import net.minecraft.predicate.NumberRange;
import org.jetbrains.annotations.Nullable;


class MobEffectInstancePredicateBuilder {
    NumberRange.IntRange amplifier = NumberRange.IntRange.ANY;
    NumberRange.IntRange duration = NumberRange.IntRange.ANY;
    @Nullable
    Boolean ambient = null;
    @Nullable
    Boolean visible = null;

    public void setAmplifier(NumberRange.IntRange bounds) {
        amplifier = bounds;
    }

    public void setDuration(NumberRange.IntRange bounds) {
        duration = bounds;
    }

    public void setAmbient(boolean bool) {
        ambient = bool;
    }

    public void setVisible(boolean bool) {
        visible = bool;
    }
}
