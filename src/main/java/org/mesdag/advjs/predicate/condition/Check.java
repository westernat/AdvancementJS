package org.mesdag.advjs.predicate.condition;

import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public interface Check {
    LootItemCondition.Builder builder();
}
