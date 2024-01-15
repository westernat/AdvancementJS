package org.mesdag.advjs.trigger;

import net.minecraft.loot.condition.LootCondition;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.condition.Check;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.HashSet;

class ItemUsedOnLocationBuilder extends AbstractTriggerBuilder implements ItemSetter {
    final ArrayList<Check> checks = new ArrayList<>();

    public void addCheck(Check check) {
        this.checks.add(check);
    }

    LootContextPredicate createContext() {
        HashSet<LootCondition> conditions = new HashSet<>();
        for (Check check : checks) {
            conditions.add(check.builder().build());
        }
        return LootContextPredicate.create(conditions.toArray(LootCondition[]::new));
    }
}
