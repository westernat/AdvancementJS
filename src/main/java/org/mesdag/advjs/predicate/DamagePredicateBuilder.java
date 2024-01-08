package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;

class DamagePredicateBuilder {
    final DamagePredicate.Builder builder = new DamagePredicate.Builder();

    @Info("Checks the amount of incoming damage before damage reduction.")
    public void dealtDamage(NumberRange.FloatRange bounds) {
        builder.dealt(bounds);
    }

    @Info("Checks the amount of incoming damage after damage reduction.")
    public void takenDamage(NumberRange.FloatRange bounds) {
        builder.taken(bounds);
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
