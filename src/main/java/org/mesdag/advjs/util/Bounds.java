package org.mesdag.advjs.util;

import net.minecraft.advancements.critereon.MinMaxBounds;
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
