package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import org.mesdag.advjs.util.Bounds;

public class UsedEnderEyeBuilder extends BaseTriggerInstanceBuilder {
    NumberRange.FloatRange distance = NumberRange.FloatRange.ANY;

    @Info("The horizontal distance between the player and the stronghold.")
    public void setDistance(Bounds distance) {
        this.distance = distance.toFloatBounds();
    }
}
