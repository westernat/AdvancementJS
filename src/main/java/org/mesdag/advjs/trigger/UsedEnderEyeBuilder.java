package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.mesdag.advjs.util.Bounds;

class UsedEnderEyeBuilder extends AbstractTriggerBuilder {
    MinMaxBounds.Doubles distance = MinMaxBounds.Doubles.ANY;

    @Info("The horizontal distance between the player and the stronghold.")
    public void setDistance(Bounds distance) {
        this.distance = distance.toDoubleBounds();
    }
}
