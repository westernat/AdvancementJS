package org.mesdag.advjs.trigger;

import net.minecraft.predicate.NumberRange;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder{
    NumberRange.FloatRange distance;

    public void setDistance(NumberRange.FloatRange distance) {
        this.distance = distance;
    }
}
