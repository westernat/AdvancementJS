package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityFlagsPredicate;

class EntityFlagsPredicateBuilder {
    final EntityFlagsPredicate.Builder builder = new EntityFlagsPredicate.Builder();

    @Info("Test whether the entity is or is not on fire.")
    public void isOnFire(Boolean bool) {
        builder.onFire(bool);
    }

    @Info("Test whether the entity is or is not sneaking.")
    public void isSneaking(Boolean bool) {
        builder.sneaking(bool);
    }

    @Info("Test whether the entity is or is not sprinting.")
    public void isSprinting(Boolean bool) {
        builder.sprinting(bool);
    }

    @Info("Test whether the entity is or is not swimming.")
    public void isSwimming(Boolean bool) {
        builder.swimming(bool);
    }

    @Info("Test whether the entity is or is not a baby variant.")
    public void isBaby(Boolean bool) {
        builder.isBaby(bool);
    }

    EntityFlagsPredicate build() {
        return builder.build();
    }
}
