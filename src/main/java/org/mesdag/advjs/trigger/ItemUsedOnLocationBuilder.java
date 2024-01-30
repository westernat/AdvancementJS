package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.List;

class ItemUsedOnLocationBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    final ArrayList<ICondition> conditions = new ArrayList<>();

    @Info("Add a predicate condition.")
    public void addCondition(ICondition condition) {
        this.conditions.add(condition);
    }

    @Info("Add predicate conditions.")
    public void addConditions(ICondition... conditions) {
        this.conditions.addAll(List.of(conditions));
    }

    ContextAwarePredicate createContext() {
        return wrapEntity(conditions.toArray(ICondition[]::new));
    }
}
