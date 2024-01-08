package org.mesdag.advjs.util;

import net.minecraft.predicate.NumberRange;

public class Bounds {
    public NumberRange.FloatRange exactly$Float(double value) {
        return NumberRange.FloatRange.exactly(value);
    }

    public NumberRange.FloatRange between$Float(double min, double max) {
        return NumberRange.FloatRange.between(min, max);
    }

    public NumberRange.FloatRange min$Float(double min) {
        return NumberRange.FloatRange.atLeast(min);
    }

    public NumberRange.FloatRange max$Float(double max) {
        return NumberRange.FloatRange.atMost(max);
    }

    public NumberRange.IntRange exactly$Integer(int value) {
        return NumberRange.IntRange.exactly(value);
    }

    public NumberRange.IntRange between$Integer(int min, int max) {
        return NumberRange.IntRange.between(min, max);
    }

    public NumberRange.IntRange min$Integer(int min) {
        return NumberRange.IntRange.atLeast(min);
    }

    public NumberRange.IntRange max$Integer(int max) {
        return NumberRange.IntRange.atMost(max);
    }
}
