package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;

class DamageSourcePredicateBuilder {
    final DamageSourcePredicate.Builder builder = new DamageSourcePredicate.Builder();

    @Info("Caused by projectile.")
    public void isProjectile(Boolean bool) {
        builder.projectile(bool);
    }

    @Info("Caused by explosion.")
    public void isExplosion(Boolean bool) {
        builder.explosion(bool);
    }

    @Info("Ignores armor reduction.")
    public void bypassesArmor(Boolean bool) {
        builder.bypassesArmor(bool);
    }

    @Info("Bypasses invulnerabilities like creative one.")
    public void bypassesInvulnerability(Boolean bool) {
        builder.bypassesInvulnerability(bool);
    }

    @Info("Bypass magic.")
    public void bypassesMagic(Boolean bool) {
        builder.bypassesMagic(bool);
    }

    @Info("Caused by fire.")
    public void isFire(Boolean bool) {
        builder.fire(bool);
    }

    @Info("Caused by magic.")
    public void isMagic(Boolean bool) {
        builder.magic(bool);
    }

    @Info("Caused by lightning bolt.")
    public void isLightning(Boolean bool) {
        builder.lightning(bool);
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(EntityPredicate entityPredicate) {
        builder.directEntity(entityPredicate);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void source(EntityPredicate entityPredicate) {
        builder.sourceEntity(entityPredicate);
    }

    DamageSourcePredicate build() {
        return builder.build();
    }
}
