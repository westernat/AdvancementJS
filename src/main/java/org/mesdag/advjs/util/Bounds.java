package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class Bounds {
    private static final Bounds ANY = new Bounds(null, null);
    private final @Nullable Number min;
    private final @Nullable Number max;

    Bounds(@Nullable Number min, @Nullable Number max) {
        this.min = min;
        this.max = max;
    }

    @HideFromJS
    public MinMaxBounds.Doubles toDoubleBounds() {
        if (min == null) {
            if (max == null) {
                return MinMaxBounds.Doubles.ANY;
            }
            return MinMaxBounds.Doubles.atMost(max.doubleValue());
        } else if (max == null) {
            return MinMaxBounds.Doubles.atLeast(min.doubleValue());
        } else if (min.equals(max)) {
            return MinMaxBounds.Doubles.exactly(min.doubleValue());
        }
        return MinMaxBounds.Doubles.between(min.doubleValue(), max.doubleValue());
    }

    @HideFromJS
    public MinMaxBounds.Ints toIntegerBounds() {
        if (min == null) {
            if (max == null) {
                return MinMaxBounds.Ints.ANY;
            }
            return MinMaxBounds.Ints.atMost(max.intValue());
        } else if (max == null) {
            return MinMaxBounds.Ints.atLeast(min.intValue());
        } else if (min.equals(max)) {
            return MinMaxBounds.Ints.exactly(min.intValue());
        }
        return MinMaxBounds.Ints.between(min.intValue(), max.intValue());
    }

    @Info("""
        A number like A will wrap to an exactly bounds.

        A list like [A, B] will wrap to a between bounds.

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
