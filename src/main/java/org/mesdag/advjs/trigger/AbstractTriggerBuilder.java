package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.PlayerPredicate;

import java.util.function.Consumer;

public abstract class AbstractTriggerBuilder {
    public ContextAwarePredicate player = ContextAwarePredicate.ANY;

    public void setPlayer(PlayerPredicate player) {
        this.player = EntityPredicate.wrap(EntityPredicate.Builder.entity().subPredicate(player).build());
    }

    public void setPlayer(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        this.player = builder.build();
    }
}
