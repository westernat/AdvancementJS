package org.mesdag.advjs.trigger.registry;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;

public class BaseTriggerInstance extends AbstractCriterionTriggerInstance {
    private final LinkedList<TriggerMatchCallback> callbacks;

    public BaseTriggerInstance(ResourceLocation id, ContextAwarePredicate player, LinkedList<TriggerMatchCallback> callbacks) {
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
