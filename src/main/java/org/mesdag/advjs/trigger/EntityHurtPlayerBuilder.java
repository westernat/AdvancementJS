package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DamagePredicate;
import org.mesdag.advjs.predicate.DamagePredicateBuilder;

import java.util.function.Consumer;

public class EntityHurtPlayerBuilder extends BaseTriggerInstanceBuilder {
    DamagePredicate damage = DamagePredicate.ANY;

    @Info("Checks the damage done to the player.")
    public void setDamageByPredicate(DamagePredicate damage) {
        this.damage = damage;
    }

    @Info("Checks the damage done to the player.")
    public void setDamage(Consumer<DamagePredicateBuilder>consumer) {
        DamagePredicateBuilder builder = new DamagePredicateBuilder();
        consumer.accept(builder);
        this.damage = builder.build();
    }
}
