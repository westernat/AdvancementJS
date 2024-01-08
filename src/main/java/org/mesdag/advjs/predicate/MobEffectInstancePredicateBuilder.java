package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import org.jetbrains.annotations.Nullable;


class MobEffectInstancePredicateBuilder {
    NumberRange.IntRange amplifier = NumberRange.IntRange.ANY;
    NumberRange.IntRange duration = NumberRange.IntRange.ANY;
    @Nullable
    Boolean ambient = null;
    @Nullable
    Boolean visible = null;

    @Info("Test if the effect's amplifier matches an exact value. Level I is represented by 0.")
    public void setAmplifier(NumberRange.IntRange bounds) {
        amplifier = bounds;
    }

    @Info("Test if the effect's remaining time (in ticks) matches an exact value.")
    public void setDuration(NumberRange.IntRange bounds) {
        duration = bounds;
    }

    @Info("Test whether the effect is from a beacon.")
    public void setAmbient(boolean bool) {
        ambient = bool;
    }

    @Info("Test if the effect has visible particles.")
    public void setVisible(boolean bool) {
        visible = bool;
    }
}
