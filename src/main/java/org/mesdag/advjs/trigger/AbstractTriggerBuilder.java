package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.PlayerPredicate;

import java.util.function.Consumer;

public abstract class AbstractTriggerBuilder {
    public EntityPredicate.Extended player = EntityPredicate.Extended.EMPTY;

    public void setPlayer(PlayerPredicate player) {
        this.player = EntityPredicate.Extended.ofLegacy(EntityPredicate.Builder.create().typeSpecific(player).build());
    }

    public void setPlayer(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        this.player = builder.build();
    }
}
