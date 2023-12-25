package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.MinMaxBounds;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    MinMaxBounds.Ints bounds = MinMaxBounds.Ints.ANY;

    public void atLeast(int amount){
        this.bounds = MinMaxBounds.Ints.atLeast(amount);
    }
}
