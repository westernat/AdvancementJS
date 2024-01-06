package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.MinMaxBounds;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    public void setLevel(MinMaxBounds.Ints level){
        this.level = level;
    }
}
