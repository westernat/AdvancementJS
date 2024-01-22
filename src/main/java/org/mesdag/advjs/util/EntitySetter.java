package org.mesdag.advjs.util;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.Arrays;

@HideFromJS
public interface EntitySetter {
    default ContextAwarePredicate wrapEntity(EntityType<?> entityType) {
        return EntityPredicate.wrap(EntityPredicate.Builder.entity().of(entityType).build());
    }

    default ContextAwarePredicate wrapEntity(EntityPredicate entityPredicate) {
        return EntityPredicate.wrap(entityPredicate);
    }

    default ContextAwarePredicate wrapEntity(ICondition... conditions) {
        return ContextAwarePredicate.create(Arrays.stream(conditions).map(condition -> condition.builder().build()).toArray(LootItemCondition[]::new));
    }

    default EntityPredicate toEntity(EntityType<?> entityType) {
        return EntityPredicate.Builder.entity().of(entityType).build();
    }
}
