package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.DistancePredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.mesdag.advjs.util.Bounds;

public class DistancePredicateBuilder {
    MinMaxBounds.Doubles x = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles y = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles z = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles horizontal = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles absolute = MinMaxBounds.Doubles.ANY;

    @Info("Test the absolute difference between the X-coordinates of the two points.")
    public void setX(Bounds x) {
        this.x = x.toDoubleBounds();
    }

    @Info("Test the absolute difference between the Y-coordinates of the two points.")
    public void setY(Bounds y) {
        this.y = y.toDoubleBounds();
    }

    @Info("Test the absolute difference between the Z-coordinates of the two points.")
    public void setZ(Bounds z) {
        this.z = z.toDoubleBounds();
    }

    @Info("Test the distance between the two points, ignoring the Y value.")
    public void setHorizontal(Bounds horizontal) {
        this.horizontal = horizontal.toDoubleBounds();
    }

    @Info("Test the distance between the two points in 3D space.")
    public void setAbsolute(Bounds absolute) {
        this.absolute = absolute.toDoubleBounds();
    }

    @HideFromJS
    public DistancePredicate build() {
        return new DistancePredicate(x, y, z, horizontal, absolute);
    }
}
