package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

class DamagePredicateBuilder {
    final DamagePredicate.Builder builder = new DamagePredicate.Builder();

    @Info("Checks the amount of incoming damage before damage reduction.")
    public void dealtDamage(MinMaxBounds.Doubles bounds) {
        builder.dealtDamage(bounds);
    }

    @Info("Checks the amount of incoming damage after damage reduction.")
    public void takenDamage(MinMaxBounds.Doubles bounds) {
        builder.takenDamage(bounds);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void sourceEntity(EntityPredicate entityPredicate) {
        builder.sourceEntity(entityPredicate);
    }

    @Info("Checks if the damage was successfully blocked.")
    public void blocked(boolean bool) {
        builder.blocked(bool);
    }

    @Info("Checks the type of damage done.")
    public void type(DamageSourcePredicate damageSourcePredicate) {
        builder.type(damageSourcePredicate);
    }

    DamagePredicate build() {
        return builder.build();
    }
}
