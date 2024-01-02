package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

class LevitationBuilder extends AbstractTriggerBuilder {
    DistancePredicate distance = DistancePredicate.ANY;
    MinMaxBounds.Ints duration = MinMaxBounds.Ints.ANY;

    @Info("The distance between the position where the player started levitating and the player's current position.")
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("The duration of the levitation in ticks.")
    public void setDuration(MinMaxBounds.Ints duration) {
        this.duration = duration;
    }
}
