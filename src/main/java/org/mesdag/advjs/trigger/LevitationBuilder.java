package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;

class LevitationBuilder extends AbstractTriggerBuilder {
    DistancePredicate distance = DistancePredicate.ANY;
    NumberRange.IntRange duration = NumberRange.IntRange.ANY;

    @Info("The distance between the position where the player started levitating and the player's current position.")
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("The duration of the levitation in ticks.")
    public void setDuration(NumberRange.IntRange duration) {
        this.duration = duration;
    }
}
