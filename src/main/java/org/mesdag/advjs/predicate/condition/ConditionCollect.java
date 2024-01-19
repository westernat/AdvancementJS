package org.mesdag.advjs.predicate.condition;


import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.LootCondition;

import java.util.Arrays;

public class ConditionCollect implements ICondition {
    final LootCondition.Builder condition;

    ConditionCollect(LootCondition.Builder condition) {
        this.condition = condition;
    }

    @HideFromJS
    public static ConditionCollect any(ICondition... conditions) {
        return new ConditionCollect(AnyOfLootCondition.builder(Arrays.stream(conditions).map(ICondition::builder).toArray(LootCondition.Builder[]::new)));
    }

    @HideFromJS
    public static ConditionCollect all(ICondition... conditions) {
        return new ConditionCollect(AllOfLootCondition.builder(Arrays.stream(conditions).map(ICondition::builder).toArray(LootCondition.Builder[]::new)));
    }

    @Override
    public LootCondition.Builder builder() {
        return condition;
    }
}
