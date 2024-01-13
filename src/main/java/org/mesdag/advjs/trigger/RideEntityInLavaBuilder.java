package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import org.mesdag.advjs.predicate.DistancePredicateBuilder;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;

import java.util.function.Consumer;

public class RideEntityInLavaBuilder extends AbstractTriggerBuilder {
    LocationPredicate startPosition = LocationPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;

    @Info("A location predicate for the last position before the player mounted the entity.")
    public void setStartPosition(LocationPredicate startPosition) {
        this.startPosition = startPosition;
    }

    @Info("A location predicate for the last position before the player mounted the entity.")
    public void setStartPosition(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder = new LocationPredicateBuilder();
        consumer.accept(builder);
        this.startPosition = builder.build();
    }

    @Info("The distance between the start position and the player's position.")
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("The distance between the start position and the player's position.")
    public void setDistance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        this.distance = builder.build();
    }
}