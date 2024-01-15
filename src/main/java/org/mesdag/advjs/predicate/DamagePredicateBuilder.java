package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class DamagePredicateBuilder {
    final DamagePredicate.Builder builder = new DamagePredicate.Builder();

    @Info("Checks the amount of incoming damage before damage reduction.")
    public void dealtDamage(Bounds bounds) {
        builder.dealt(bounds.toFloatBounds());
    }

    @Info("Checks the amount of incoming damage after damage reduction.")
    public void takenDamage(Bounds bounds) {
        builder.taken(bounds.toFloatBounds());
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void sourceEntity(EntityPredicate entityPredicate) {
        builder.sourceEntity(entityPredicate);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void sourceEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.sourceEntity(builder1.build());
    }

    @Info("Checks if the damage was successfully blocked.")
    public void blocked(boolean bool) {
        builder.blocked(bool);
    }

    @Info("Checks the type of damage done.")
    public void type(DamageSourcePredicate damageSourcePredicate) {
        builder.type(damageSourcePredicate);
    }

    @Info("Checks the type of damage done.")
    public void type(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder1 = new DamageSourcePredicateBuilder();
        consumer.accept(builder1);
        builder.type(builder1.build());
    }

    @HideFromJS
    public DamagePredicate build() {
        return builder.build();
    }
}
