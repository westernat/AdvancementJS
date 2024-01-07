package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.material.Fluid;

class FluidPredicateBuilder {
    final FluidPredicate.Builder builder = FluidPredicate.Builder.fluid();

    @Info("Check fluid.")
    public void of(Fluid fluid) {
        builder.of(fluid);
    }

    @Info("Check states of fluid.")
    public void setProperties(StatePropertiesPredicate statePropertiesPredicate) {
        builder.setProperties(statePropertiesPredicate);
    }

    FluidPredicate build() {
        return builder.build();
    }
}
