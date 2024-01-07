package org.mesdag.advjs.util;

import net.minecraft.advancements.critereon.MinMaxBounds;

public class Bounds {
    public MinMaxBounds.Doubles exactly$Double(double value) {
        return MinMaxBounds.Doubles.exactly(value);
    }

    public MinMaxBounds.Doubles between$Double(double min, double max) {
        return MinMaxBounds.Doubles.between(min, max);
    }

    public MinMaxBounds.Doubles min$Double(double min) {
        return MinMaxBounds.Doubles.atLeast(min);
    }

    public MinMaxBounds.Doubles max$Double(double max) {
        return MinMaxBounds.Doubles.atMost(max);
    }

    public MinMaxBounds.Ints exactly$Integer(int value) {
        return MinMaxBounds.Ints.exactly(value);
    }

    public MinMaxBounds.Ints between$Integer(int min, int max) {
        return MinMaxBounds.Ints.between(min, max);
    }

    public MinMaxBounds.Ints min$Integer(int min) {
        return MinMaxBounds.Ints.atLeast(min);
    }

    public MinMaxBounds.Ints max$Integer(int max) {
        return MinMaxBounds.Ints.atMost(max);
    }
}
