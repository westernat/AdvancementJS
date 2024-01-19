package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.PlayerPredicate;
import org.mesdag.advjs.predicate.PlayerPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public abstract class AbstractTriggerBuilder implements EntitySetter {
    public LootContextPredicate player = LootContextPredicate.EMPTY;

    public void setPlayer(PlayerPredicate player) {
        this.player = wrapEntity(EntityPredicate.Builder.create().typeSpecific(player).build());
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
