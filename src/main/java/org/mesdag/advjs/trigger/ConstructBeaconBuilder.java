package org.mesdag.advjs.trigger;


import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    NumberRange.IntRange bounds = NumberRange.IntRange.ANY;

    @Info("The level of the updated beacon structure.")
    public void atLeast(int amount){
        this.bounds = NumberRange.IntRange.atLeast(amount);
    }
}
