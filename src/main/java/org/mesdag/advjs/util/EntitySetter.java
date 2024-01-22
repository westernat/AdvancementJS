package org.mesdag.advjs.util;


import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.Arrays;

@HideFromJS
public interface EntitySetter {
    default LootContextPredicate wrapEntity(EntityType<?> entityType) {
        return EntityPredicate.asLootContextPredicate(EntityPredicate.Builder.create().type(entityType).build());
    }

    default LootContextPredicate wrapEntity(EntityPredicate entityPredicate) {
        return EntityPredicate.asLootContextPredicate(entityPredicate);
    }

    default LootContextPredicate wrapEntity(ICondition... conditions) {
        return LootContextPredicate.create(Arrays.stream(conditions).map(condition -> condition.builder().build()).toArray(LootCondition[]::new));
    }

    default EntityPredicate toEntity(EntityType<?> entityType) {
        return EntityPredicate.Builder.create().type(entityType).build();
    }
}
