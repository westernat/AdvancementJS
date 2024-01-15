package org.mesdag.advjs.predicate.condition;


import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.AllOfLootCondition;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.LootCondition;

import java.util.Arrays;

public class Condition implements Check {
    final LootCondition.Builder condition;

    Condition(LootCondition.Builder condition) {
        this.condition = condition;
    }

    @HideFromJS
    public static Condition any(Check... checks) {
        return new Condition(AnyOfLootCondition.builder(Arrays.stream(checks).map(Check::builder).toArray(LootCondition.Builder[]::new)));
    }

    @HideFromJS
    public static Condition all(Check... checks) {
        return new Condition(AllOfLootCondition.builder(Arrays.stream(checks).map(Check::builder).toArray(LootCondition.Builder[]::new)));
    }

    @Override
    public LootCondition.Builder builder() {
        return condition;
    }
}
