package org.mesdag.advjs.predicate.condition;


import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Arrays;

public class ConditionCollect implements ICondition {
    final LootItemCondition.Builder condition;

    ConditionCollect(LootItemCondition.Builder condition) {
        this.condition = condition;
    }

    @HideFromJS
    public static ConditionCollect any(ICondition... conditions) {
        return new ConditionCollect(AnyOfCondition.anyOf(Arrays.stream(conditions).map(ICondition::builder).toArray(LootItemCondition.Builder[]::new)));
    }

    @HideFromJS
    public static ConditionCollect all(ICondition... conditions) {
        return new ConditionCollect(AllOfCondition.allOf(Arrays.stream(conditions).map(ICondition::builder).toArray(LootItemCondition.Builder[]::new)));
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return condition;
    }
}
