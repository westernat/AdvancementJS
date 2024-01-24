package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import org.mesdag.advjs.util.Bounds;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder {
    NumberRange.FloatRange distance = NumberRange.FloatRange.ANY;

    @Info("The horizontal distance between the player and the stronghold.")
    public void setDistance(Bounds distance) {
        this.distance = distance.toFloatBounds();
    }
}
