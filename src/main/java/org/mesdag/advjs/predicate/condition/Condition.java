package org.mesdag.advjs.predicate.condition;


import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.AnyOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.Arrays;

public class Condition implements Check {
    final LootItemCondition.Builder condition;

    Condition(LootItemCondition.Builder condition) {
        this.condition = condition;
    }

    @HideFromJS
    public static Condition any(Check... checks) {
        return new Condition(AnyOfCondition.anyOf(Arrays.stream(checks).map(Check::builder).toArray(LootItemCondition.Builder[]::new)));
    }

    @HideFromJS
    public static Condition all(Check... checks) {
        return new Condition(AllOfCondition.allOf(Arrays.stream(checks).map(Check::builder).toArray(LootItemCondition.Builder[]::new)));
    }

    @Override
    public LootItemCondition.Builder builder() {
        return condition;
    }
}
