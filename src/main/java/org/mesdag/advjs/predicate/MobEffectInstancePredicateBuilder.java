package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import org.mesdag.advjs.util.Bounds;

import javax.annotation.Nullable;

public class MobEffectInstancePredicateBuilder {
    MinMaxBounds.Ints amplifier = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;
    @Nullable
    Boolean ambient = null;
    @Nullable
    Boolean visible = null;

    @Info("Test if the effect's amplifier matches an exact value. Level I is represented by 0.")
    public void setAmplifier(Bounds bounds) {
        amplifier = bounds.toIntegerBounds();
    }

    @Info("Test if the effect's remaining time (in ticks) matches an exact value.")
    public void setDuration(Bounds bounds) {
        duration = bounds.toIntegerBounds();
    }

    @Info("Test whether the effect is from a beacon.")
    public void setAmbient(boolean bool) {
        ambient = bool;
    }

    @Info("Test if the effect has visible particles.")
    public void setVisible(boolean bool) {
        visible = bool;
    }

    MobEffectsPredicate.MobEffectInstancePredicate build() {
        return new MobEffectsPredicate.MobEffectInstancePredicate(amplifier, duration, ambient, visible);
    }
}
