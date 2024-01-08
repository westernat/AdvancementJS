package org.mesdag.advjs.util;


import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;

public interface EntitySetter {
    default LootContextPredicate warpEntity(EntityType<?> entityType) {
        return EntityPredicate.asLootContextPredicate(EntityPredicate.Builder.create().type(entityType).build());
    }
}
