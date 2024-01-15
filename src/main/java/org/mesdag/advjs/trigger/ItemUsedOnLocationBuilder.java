package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.mesdag.advjs.predicate.condition.Check;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.HashSet;

class ItemUsedOnLocationBuilder extends AbstractTriggerBuilder implements ItemSetter {
    final ArrayList<Check> checks = new ArrayList<>();

    public void addCheck(Check check) {
        this.checks.add(check);
    }

    ContextAwarePredicate createContext() {
        HashSet<LootItemCondition> conditions = new HashSet<>();
        for (Check check : checks) {
            conditions.add(check.builder().build());
        }
        return ContextAwarePredicate.create(conditions.toArray(LootItemCondition[]::new));
    }
}
