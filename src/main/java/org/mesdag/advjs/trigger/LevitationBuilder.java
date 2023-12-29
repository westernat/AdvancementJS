package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

class LevitationBuilder extends AbstractTriggerBuilder {
    DistancePredicate distance = DistancePredicate.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;

    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    public void setDuration(MinMaxBounds.Ints duration) {
        this.duration = duration;
    }
}
