package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    @Info("The level of the updated beacon structure.")
    public void setLevel(MinMaxBounds.Ints level){
        this.level = level;
    }
}
