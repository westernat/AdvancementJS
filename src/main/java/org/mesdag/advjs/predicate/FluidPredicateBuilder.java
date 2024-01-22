package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.material.Fluid;

import java.util.function.Consumer;

public class FluidPredicateBuilder {
    final FluidPredicate.Builder builder = FluidPredicate.Builder.fluid();

    @Info("Check fluid.")
    public void of(Fluid fluid) {
        builder.of(fluid);
    }

    @Info("Check states of fluid.")
    public void setPropertiesByPredicate(StatePropertiesPredicate statePropertiesPredicate) {
        builder.setProperties(statePropertiesPredicate);
    }

    @Info("Check states of fluid.")
    public void setProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder1 = new StatePropertiesPredicateBuilder();
        consumer.accept(builder1);
        builder.setProperties(builder1.build());
    }

    FluidPredicate build() {
        return builder.build();
    }
}
