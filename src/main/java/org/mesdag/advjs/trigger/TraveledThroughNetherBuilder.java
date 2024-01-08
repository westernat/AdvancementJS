package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;

public class TraveledThroughNetherBuilder extends AbstractTriggerBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    @Info("A location predicate for the last position before the player teleported to the Nether.")
    public void setStartPosition(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    @Info("The distance between the position where the player teleported to the Nether and the player's position when they returned.")
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }
}