package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.PlayerPredicate;
import org.mesdag.advjs.predicate.PlayerPredicateBuilder;

import java.util.function.Consumer;

public abstract class AbstractTriggerBuilder {
    public LootContextPredicate player = LootContextPredicate.EMPTY;

    public void setPlayer(PlayerPredicate player) {
        this.player = EntityPredicate.asLootContextPredicate(EntityPredicate.Builder.create().typeSpecific(player).build());
    }

    public void setPlayer(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        this.player = builder.build();
    }
}
