package org.mesdag.advjs.predicate;


import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import org.mesdag.advjs.util.Bounds;

public class DistancePredicateBuilder {
    NumberRange.FloatRange x = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange y = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange z = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange horizontal = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange absolute = NumberRange.FloatRange.ANY;

    @Info("Test the absolute difference between the X-coordinates of the two points.")
    public void setX(Bounds x) {
        this.x = x.toFloatBounds();
    }

    @Info("Test the absolute difference between the Y-coordinates of the two points.")
    public void setY(Bounds y) {
        this.y = y.toFloatBounds();
    }

    @Info("Test the absolute difference between the Z-coordinates of the two points.")
    public void setZ(Bounds z) {
        this.z = z.toFloatBounds();
    }

    @Info("Test the distance between the two points, ignoring the Y value.")
    public void setHorizontal(Bounds horizontal) {
        this.horizontal = horizontal.toFloatBounds();
    }

    @Info("Test the distance between the two points in 3D space.")
    public void setAbsolute(Bounds absolute) {
        this.absolute = absolute.toFloatBounds();
    }

    @HideFromJS
    public DistancePredicate build() {
        return new DistancePredicate(x, y, z, horizontal, absolute);
    }
}
