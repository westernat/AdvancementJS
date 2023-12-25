package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.MinMaxBounds;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder{
    MinMaxBounds.Doubles distance;

    public void setDistance(MinMaxBounds.Doubles distance) {
        this.distance = distance;
    }
}
