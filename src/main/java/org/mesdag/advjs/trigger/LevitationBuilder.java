package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.mesdag.advjs.predicate.DistancePredicateBuilder;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

class LevitationBuilder extends AbstractTriggerBuilder {
    DistancePredicate distance = DistancePredicate.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;

    @Info("The distance between the position where the player started levitating and the player's current position.")
    public void setDistanceByPredicate(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("The distance between the position where the player started levitating and the player's current position.")
    public void setDistance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        this.distance = builder.build();
    }

    @Info("The duration of the levitation in ticks.")
    public void setDuration(Bounds duration) {
        this.duration = duration.toIntegerBounds();
    }
}
