package org.mesdag.advjs.util;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;

public interface EntitySetter {
    default ContextAwarePredicate warpEntity(EntityType<?> entityType) {
        return EntityPredicate.wrap(EntityPredicate.Builder.entity().of(entityType).build());
    }
}
