package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.fluid.Fluid;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.StatePredicate;

class FluidPredicateBuilder {
    final FluidPredicate.Builder builder = FluidPredicate.Builder.create();

    @Info("Check fluid.")
    public void of(Fluid fluid) {
        builder.fluid(fluid);
    }

    @Info("Check states of fluid.")
    public void setProperties(StatePredicate statePropertiesPredicate) {
        builder.state(statePropertiesPredicate);
    }

    FluidPredicate build() {
        return builder.build();
    }
}
