package org.mesdag.advjs.predicate;


import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;

public class DistancePredicateBuilder {
    NumberRange.FloatRange x = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange y = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange z = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange horizontal = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange absolute = NumberRange.FloatRange.ANY;

    @Info("Test the absolute difference between the X-coordinates of the two points.")
    public void setX(NumberRange.FloatRange x) {
        this.x = x;
    }

    @Info("Test the absolute difference between the Y-coordinates of the two points.")
    public void setY(NumberRange.FloatRange y) {
        this.y = y;
    }

    @Info("Test the absolute difference between the Z-coordinates of the two points.")
    public void setZ(NumberRange.FloatRange z) {
        this.z = z;
    }

    @Info("Test the distance between the two points, ignoring the Y value.")
    public void setHorizontal(NumberRange.FloatRange horizontal) {
        this.horizontal = horizontal;
    }

    @Info("Test the distance between the two points in 3D space.")
    public void setAbsolute(NumberRange.FloatRange absolute) {
        this.absolute = absolute;
    }

    @HideFromJS
    public DistancePredicate build() {
        return new DistancePredicate(x, y, z, horizontal, absolute);
    }
}
