package org.mesdag.advjs.trigger;

import net.minecraft.predicate.PlayerPredicate;
import net.minecraft.predicate.entity.EntityPredicate;

import java.util.function.Consumer;

abstract class AbstractTriggerBuilder {
    EntityPredicate.Extended player = EntityPredicate.Extended.EMPTY;

    public void setPlayer(PlayerPredicate player) {
        this.player = EntityPredicate.Extended.ofLegacy(EntityPredicate.Builder.create().player(player).build());
    }

    public void setPlayer(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        this.player = builder.build();
    }
}
