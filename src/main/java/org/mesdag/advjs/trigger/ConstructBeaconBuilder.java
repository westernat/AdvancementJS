package org.mesdag.advjs.trigger;


import net.minecraft.predicate.NumberRange;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    NumberRange.IntRange level = NumberRange.IntRange.ANY;

    public void setLevel(NumberRange.IntRange level){
        this.level = level;
    }
}
