package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;

public class EntityFlagsPredicateBuilder {
    final EntityFlagsPredicate.Builder builder = new EntityFlagsPredicate.Builder();

    @Info("Test whether the entity is or is not on fire.")
    public void isOnFire(boolean bool) {
        builder.setOnFire(bool);
    }

    @Info("Test whether the entity is or is not sneaking.")
    public void isSneaking(boolean bool) {
        builder.setCrouching(bool);
    }

    @Info("Test whether the entity is or is not sprinting.")
    public void isSprinting(boolean bool) {
        builder.setSprinting(bool);
    }

    @Info("Test whether the entity is or is not swimming.")
    public void isSwimming(boolean bool) {
        builder.setSwimming(bool);
    }

    @Info("Test whether the entity is or is not a baby variant.")
    public void isBaby(boolean bool) {
        builder.setIsBaby(bool);
    }

    EntityFlagsPredicate build() {
        return builder.build();
    }
}
