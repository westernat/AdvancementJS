package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;

public class FallFromHeightBuilder extends AbstractTriggerBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    public void setStartPosition(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }
}
