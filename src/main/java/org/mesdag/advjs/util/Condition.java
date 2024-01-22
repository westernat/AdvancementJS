package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.predicate.condition.*;

import java.util.function.Consumer;

public class Condition {
    @Info("Evaluates a list of predicates and passes if any one of them passes.")
    public ConditionCollect anyOf(ICondition... conditions) {
        return ConditionCollect.any(conditions);
    }

    @Info("Evaluates a list of predicates and passes if all of them pass.")
    public ConditionCollect allOf(ICondition... conditions) {
        return ConditionCollect.all(conditions);
    }

    @Info("Checks the current location against location criteria.")
    public LocationCheckCondition locationCheck() {
        return new LocationCheckCondition();
    }

    @Info("Checks tool used to mine the block.")
    public MatchToolCondition matchTool(Consumer<ItemPredicateBuilder> consumer) {
        return new MatchToolCondition(consumer);
    }

    @Info("Checks the block and its block states.")
    public StatePropertyCondition blockStateProperty(Block block) {
        return new StatePropertyCondition(block);
    }

    @Info("Checks properties of an entity.")
    public EntityPropertyCondition entityPropertyByPredicate(EntityPredicate entityPredicate) {
        return new EntityPropertyCondition(entityPredicate);
    }

    @Info("Checks properties of an entity.")
    public EntityPropertyCondition entityProperty(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        return new EntityPropertyCondition(builder.build());
    }
}
