package org.mesdag.advjs.predicate;

import net.minecraft.advancements.critereon.MinMaxBounds;

import javax.annotation.Nullable;

class MobEffectInstancePredicateBuilder {
    MinMaxBounds.Ints amplifier = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;
    @Nullable
    Boolean ambient = null;
    @Nullable
    Boolean visible = null;

    public void setAmplifier(MinMaxBounds.Ints bounds) {
        amplifier = bounds;
    }

    public void setDuration(MinMaxBounds.Ints bounds) {
        duration = bounds;
    }

    public void setAmbient(boolean bool) {
        ambient = bool;
    }

    public void setVisible(boolean bool) {
        visible = bool;
    }
}
