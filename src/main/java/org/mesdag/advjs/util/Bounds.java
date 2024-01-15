package org.mesdag.advjs.util;

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

    @SuppressWarnings("unchecked")
    public static Bounds of(Object o) {
        if (o instanceof Number n) {
            var d = n.doubleValue();
            return new Bounds(d, d);
        } else if (o instanceof List l && !l.isEmpty()) {
            var min = (Number) l.get(0);
            var max = l.size() >= 2 ? (Number) l.get(1) : min;
            return new Bounds(min.doubleValue(), max.doubleValue());
        } else if (o instanceof Map) {
            var m = (Map<String, Object>) o;
            return new Bounds(m.containsKey("min") ? (Number) m.get("min") : null, m.containsKey("max") ? (Number) m.get("max") : null);
        }

        return ANY;
    }
}
