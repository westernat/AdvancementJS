package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.NumberRange;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class Bounds {
    private static final Bounds ANY = new Bounds(null, null);

    @Nullable
    private final Number min;
    @Nullable
    private final Number max;

    Bounds(@Nullable Number min, @Nullable Number max) {
        this.min = min;
        this.max = max;
    }

    @HideFromJS
    public NumberRange.FloatRange toFloatBounds() {
        if (min == null) {
            if (max == null) {
                return NumberRange.FloatRange.ANY;
            }
            return NumberRange.FloatRange.atMost(max.doubleValue());
        } else if (max == null) {
            return NumberRange.FloatRange.atLeast(min.doubleValue());
        } else if (min.equals(max)) {
            return NumberRange.FloatRange.exactly(min.doubleValue());
        }
        return NumberRange.FloatRange.between(min.doubleValue(), max.doubleValue());
    }

    @HideFromJS
    public NumberRange.IntRange toIntegerBounds() {
        if (min == null) {
            if (max == null) {
                return NumberRange.IntRange.ANY;
            }
            return NumberRange.IntRange.atMost(max.intValue());
        } else if (max == null) {
            return NumberRange.IntRange.atLeast(min.intValue());
        } else if (min.equals(max)) {
            return NumberRange.IntRange.exactly(min.intValue());
        }
        return NumberRange.IntRange.between(min.intValue(), max.intValue());
    }

    @Info("""
        A number like A will wrap to an exactly bounds.

        A list like [A, B] will wrap to an between bounds.

        A map like {min: A} will wrap to an atLeast bounds.

        A map like {max: B} will wrap to an atMost bounds.
        """)
    @SuppressWarnings("unchecked")
    public static Bounds of(Object o) {
        if (o instanceof Bounds bounds) {
            return bounds;
        } else if (o instanceof Number n) {
            double d = n.doubleValue();
            return new Bounds(d, d);
        } else if (o instanceof List<?> l && !l.isEmpty()) {
            Number min = (Number) l.get(0);
            Number max = l.size() >= 2 ? (Number) l.get(1) : min;
            return new Bounds(min.doubleValue(), max.doubleValue());
        } else if (o instanceof Map) {
            Map<String, Object> m = (Map<String, Object>) o;
            return new Bounds((Number) m.get("min"), (Number) m.get("max"));
        }

        return ANY;
    }

    public static Bounds exactly(double a) {
        return new Bounds(a, a);
    }

    public static Bounds between(double a, double b) {
        return new Bounds(a, b);
    }

    public static Bounds atLeast(double a) {
        return new Bounds(a, null);
    }

    public static Bounds atMost(double b) {
        return new Bounds(null, b);
    }

    public static Bounds any() {
        return ANY;
    }
}
