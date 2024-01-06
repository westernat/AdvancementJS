package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.LocationPredicate;

public class RideEntityInLavaBuilder extends AbstractTriggerBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    public void setStartPosition(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }
}
