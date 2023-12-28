package org.mesdag.advjs.util;


import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;

public interface EntitySetter {
    default EntityPredicate.Extended warpEntity(Identifier entityId) {
        return EntityPredicate.Extended.ofLegacy(EntityPredicate.Builder.create().type(entityId).build());
    }

    default EntityPredicate.Extended warpEntity(EntityType<?> entityType) {
        return EntityPredicate.Extended.ofLegacy(EntityPredicate.Builder.create().type(entityType).build());
    }
}
