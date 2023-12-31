package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;

import javax.annotation.Nullable;

class MobEffectInstancePredicateBuilder {
    MinMaxBounds.Ints amplifier = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;
    @Nullable
    Boolean ambient = null;
    @Nullable
    Boolean visible = null;

    @Info("Test if the effect's amplifier matches an exact value. Level I is represented by 0.")
    public void setAmplifier(MinMaxBounds.Ints bounds) {
        amplifier = bounds;
    }

    @Info("Test if the effect's remaining time (in ticks) matches an exact value.")
    public void setDuration(MinMaxBounds.Ints bounds) {
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
