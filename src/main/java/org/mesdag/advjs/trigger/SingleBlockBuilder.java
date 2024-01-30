package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.predicate.StatePropertiesPredicateBuilder;

import java.util.function.Consumer;

class SingleBlockBuilder extends BaseTriggerInstanceBuilder {
    @Nullable
    Block block = null;
    StatePropertiesPredicate state = StatePropertiesPredicate.ANY;

    public void setBlock(@Nullable Block block) {
        this.block = block;
    }

    public void setStateByPredicate(StatePropertiesPredicate state) {
        this.state = state;
    }

    public void setState(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder = new StatePropertiesPredicateBuilder();
        consumer.accept(builder);
        this.state = builder.build();
    }
}
