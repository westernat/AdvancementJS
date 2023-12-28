package org.mesdag.advjs.trigger;

import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;

class LevitationBuilder extends AbstractTriggerBuilder {
    DistancePredicate distance = DistancePredicate.ANY;
    NumberRange.IntRange duration = NumberRange.IntRange.ANY;

    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    public void setDuration(NumberRange.IntRange duration) {
        this.duration = duration;
    }
}
