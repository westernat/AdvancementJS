package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder{
    MinMaxBounds.Doubles distance;

    @Info("The horizontal distance between the player and the stronghold.")
    public void setDistance(MinMaxBounds.Doubles distance) {
        this.distance = distance;
    }
}
