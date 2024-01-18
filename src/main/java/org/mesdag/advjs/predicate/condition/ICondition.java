package org.mesdag.advjs.predicate.condition;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public interface ICondition {
    LootItemCondition.Builder builder();
}
