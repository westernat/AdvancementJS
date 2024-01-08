package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;

public class RideEntityInLavaBuilder extends AbstractTriggerBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    @Info("A location predicate for the last position before the player mounted the entity.")
    public void setStartPosition(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    @Info("The distance between the start position and the player's position.")
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }
}