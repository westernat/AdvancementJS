package org.mesdag.advjs.predicate;

import net.minecraft.advancements.critereon.MinMaxBounds;

class DistancePredicateBuilder {
    MinMaxBounds.Doubles x = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles y = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles z = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles horizontal = MinMaxBounds.Doubles.ANY;
    MinMaxBounds.Doubles absolute = MinMaxBounds.Doubles.ANY;

    public void setX(MinMaxBounds.Doubles x) {
        this.x = x;
    }

    public void setY(MinMaxBounds.Doubles y) {
        this.y = y;
    }

    public void setZ(MinMaxBounds.Doubles z) {
        this.z = z;
    }

    public void setHorizontal(MinMaxBounds.Doubles horizontal) {
        this.horizontal = horizontal;
    }

    public void setAbsolute(MinMaxBounds.Doubles absolute) {
        this.absolute = absolute;
    }
}
