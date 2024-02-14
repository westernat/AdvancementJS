package org.mesdag.advjs.trigger.registry;

import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.util.Identifier;

import java.util.LinkedList;

public class CustomTriggerInstance extends AbstractCriterionConditions {
    private final LinkedList<TriggerMatchCallback> callbacks;

    public CustomTriggerInstance(Identifier id, LootContextPredicate player, LinkedList<TriggerMatchCallback> callbacks) {
        super(id, player);
        this.callbacks = callbacks;
    }

    public boolean matches(Object[] tests) {
        if (callbacks.size() != tests.length) return false;
        for (int i = 0; i < tests.length; i++) {
            if (!callbacks.get(i).match(tests[i])) return false;
        }
        return true;
    }
}
