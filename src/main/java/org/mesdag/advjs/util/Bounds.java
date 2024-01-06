package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.MinMaxBounds;

public class Bounds {
    @Info(value = "Get a double bounds of exactly value.", params = @Param(name = "value"))
    public MinMaxBounds.Doubles exactly$Double(double value) {
        return MinMaxBounds.Doubles.exactly(value);
    }

    @Info(value = "Get a double bounds between min and max",
        params = {
            @Param(name = "min"),
            @Param(name = "max")
        })
    public MinMaxBounds.Doubles between$Double(double min, double max) {
        return MinMaxBounds.Doubles.between(min, max);
    }

    @Info(value = "Get a double bounds of min value.", params = @Param(name = "min"))
    public MinMaxBounds.Doubles min$Double(double min) {
        return MinMaxBounds.Doubles.atLeast(min);
    }

    @Info(value = "Get a double bounds of max value.", params = @Param(name = "max"))
    public MinMaxBounds.Doubles max$Double(double max) {
        return MinMaxBounds.Doubles.atMost(max);
    }

    @Info(value = "Get an integer bounds of exactly value.", params = @Param(name = "value"))
    public MinMaxBounds.Ints exactly$Integer(int value) {
        return MinMaxBounds.Ints.exactly(value);
    }

    @Info(value = "Get an integer bounds between min and max",
        params = {
            @Param(name = "min"),
            @Param(name = "max")
        })
    public MinMaxBounds.Ints between$Integer(int min, int max) {
        return MinMaxBounds.Ints.between(min, max);
    }

    @Info(value = "Get an integer bounds of min value.", params = @Param(name = "min"))
    public MinMaxBounds.Ints min$Integer(int min) {
        return MinMaxBounds.Ints.atLeast(min);
    }

    @Info(value = "Get an integer bounds of max value.", params = @Param(name = "max"))
    public MinMaxBounds.Ints max$Integer(int max) {
        return MinMaxBounds.Ints.atMost(max);
    }
}
