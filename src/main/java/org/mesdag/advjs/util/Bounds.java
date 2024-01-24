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
        if (o instanceof Number n) {
            var d = n.doubleValue();
            return new Bounds(d, d);
        } else if (o instanceof List<?> l && !l.isEmpty()) {
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
