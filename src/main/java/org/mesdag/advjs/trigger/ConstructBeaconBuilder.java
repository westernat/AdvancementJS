package org.mesdag.advjs.trigger;


import net.minecraft.predicate.NumberRange;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    NumberRange.IntRange bounds = NumberRange.IntRange.ANY;

    public void atLeast(int amount){
        this.bounds = NumberRange.IntRange.atLeast(amount);
    }
}
