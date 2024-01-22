package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.fluid.Fluid;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.StatePredicate;

import java.util.function.Consumer;

class FluidPredicateBuilder {
    final FluidPredicate.Builder builder = FluidPredicate.Builder.create();

    @Info("Check fluid.")
    public void of(Fluid fluid) {
        builder.fluid(fluid);
    }

    @Info("Check states of fluid.")
    public void setPropertiesByPredicate(StatePredicate statePropertiesPredicate) {
        builder.state(statePropertiesPredicate);
    }

    @Info("Check states of fluid.")
    public void setProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder1 = new StatePropertiesPredicateBuilder();
        consumer.accept(builder1);
        builder.state(builder1.build());
    }

    FluidPredicate build() {
        return builder.build();
    }
}
