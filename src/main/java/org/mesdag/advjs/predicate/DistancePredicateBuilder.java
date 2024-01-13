package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

public class DistancePredicateBuilder {
    MinMaxBounds.Doubles x = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles y = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles z = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles horizontal = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles absolute = MinMaxBounds.Doubles.ANY;

    @Info("Test the absolute difference between the X-coordinates of the two points.")
    public void setX(MinMaxBounds.Doubles x) {
        this.x = x;
    }

    @Info("Test the absolute difference between the Y-coordinates of the two points.")
    public void setY(MinMaxBounds.Doubles y) {
        this.y = y;
    }

    @Info("Test the absolute difference between the Z-coordinates of the two points.")
    public void setZ(MinMaxBounds.Doubles z) {
        this.z = z;
    }

    @Info("Test the distance between the two points, ignoring the Y value.")
    public void setHorizontal(MinMaxBounds.Doubles horizontal) {
        this.horizontal = horizontal;
    }

    @Info("Test the distance between the two points in 3D space.")
    public void setAbsolute(MinMaxBounds.Doubles absolute) {
        this.absolute = absolute;
    }

    @HideFromJS
    public DistancePredicate build() {
        return new DistancePredicate(x, y, z, horizontal, absolute);
    }
}
