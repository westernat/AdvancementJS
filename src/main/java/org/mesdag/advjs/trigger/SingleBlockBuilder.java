package org.mesdag.advjs.trigger;


import net.minecraft.block.Block;
import net.minecraft.predicate.StatePredicate;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.predicate.StatePropertiesPredicateBuilder;

import java.util.function.Consumer;


public class SingleBlockBuilder extends BaseTriggerInstanceBuilder {
    @Nullable
    Block block = null;
    StatePredicate state = StatePredicate.ANY;

    public void setBlock(@Nullable Block block) {
        this.block = block;
    }

    public void setStateByPredicate(StatePredicate state) {
        this.state = state;
    }

    public void setState(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder = new StatePropertiesPredicateBuilder();
        consumer.accept(builder);
        this.state = builder.build();
    }
}
