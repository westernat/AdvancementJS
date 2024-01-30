package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import org.mesdag.advjs.predicate.DistancePredicateBuilder;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;

import java.util.function.Consumer;

public class TraveledThroughNetherBuilder extends BaseTriggerInstanceBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    @Info("A location predicate for the last position before the player teleported to the Nether.")
    public void setStartPositionByPredicate(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    @Info("A location predicate for the last position before the player teleported to the Nether.")
    public void setStartPosition(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder = new LocationPredicateBuilder();
        consumer.accept(builder);
        this.startPosition = builder.build();
    }

    @Info("The distance between the position where the player teleported to the Nether and the player's position when they returned.")
    public void setDistanceByPredicate(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("The distance between the position where the player teleported to the Nether and the player's position when they returned.")
    public void setDistance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        this.distance = builder.build();
    }
}
