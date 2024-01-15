package org.mesdag.advjs.trigger;


import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import org.mesdag.advjs.util.Bounds;

class ConstructBeaconBuilder extends AbstractTriggerBuilder {
    NumberRange.IntRange level = NumberRange.IntRange.ANY;

    @Info("The level of the updated beacon structure.")
    public void setLevel(Bounds level){
        this.level = level.toIntegerBounds();
    }
}
