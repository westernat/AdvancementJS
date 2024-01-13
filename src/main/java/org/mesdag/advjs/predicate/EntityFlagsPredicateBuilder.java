package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;

public class EntityFlagsPredicateBuilder {
    final EntityFlagsPredicate.Builder builder = new EntityFlagsPredicate.Builder();

    @Info("Test whether the entity is or is not on fire.")
    public void isOnFire(Boolean bool) {
        builder.setOnFire(bool);
    }

    @Info("Test whether the entity is or is not sneaking.")
    public void isSneaking(Boolean bool) {
        builder.setCrouching(bool);
    }

    @Info("Test whether the entity is or is not sprinting.")
    public void isSprinting(Boolean bool) {
        builder.setSprinting(bool);
    }

    @Info("Test whether the entity is or is not swimming.")
    public void isSwimming(Boolean bool) {
        builder.setSwimming(bool);
    }

    @Info("Test whether the entity is or is not a baby variant.")
    public void isBaby(Boolean bool) {
        builder.setIsBaby(bool);
    }

    EntityFlagsPredicate build() {
        return builder.build();
    }
}
