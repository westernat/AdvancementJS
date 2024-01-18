package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.PlayerPredicate;
import org.mesdag.advjs.predicate.PlayerPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public abstract class AbstractTriggerBuilder implements EntitySetter {
    public ContextAwarePredicate player = ContextAwarePredicate.ANY;

    public void setPlayer(PlayerPredicate player) {
        this.player = wrapEntity(EntityPredicate.Builder.entity().subPredicate(player).build());
    }

    public void setPlayer(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        this.player = builder.build();
    }

    public void setPlayer(ICondition... conditions) {
        this.player = wrapEntity(conditions);
    }
}
