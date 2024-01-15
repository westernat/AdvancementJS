package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.mesdag.advjs.util.Bounds;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    @Info("The level of the updated beacon structure.")
    public void setLevel(Bounds level){
        this.level = level.toIntegerBounds();
    }
}
