package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder{
    NumberRange.FloatRange distance;

    @Info("The horizontal distance between the player and the stronghold.")
    public void setDistance(NumberRange.FloatRange distance) {
        this.distance = distance;
    }
}
