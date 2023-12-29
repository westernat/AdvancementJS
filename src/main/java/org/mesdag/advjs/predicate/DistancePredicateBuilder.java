package org.mesdag.advjs.predicate;


import net.minecraft.predicate.NumberRange;

class DistancePredicateBuilder {
    NumberRange.FloatRange x = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange y = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange z = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange horizontal = NumberRange.FloatRange.ANY;
    NumberRange.FloatRange absolute = NumberRange.FloatRange.ANY;

    public void setX(NumberRange.FloatRange x) {
        this.x = x;
    }

    public void setY(NumberRange.FloatRange y) {
        this.y = y;
    }

    public void setZ(NumberRange.FloatRange z) {
        this.z = z;
    }

    public void setHorizontal(NumberRange.FloatRange horizontal) {
        this.horizontal = horizontal;
    }

    public void setAbsolute(NumberRange.FloatRange absolute) {
        this.absolute = absolute;
    }
}
