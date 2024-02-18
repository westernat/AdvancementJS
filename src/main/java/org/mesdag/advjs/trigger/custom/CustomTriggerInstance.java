package org.mesdag.advjs.trigger.custom;

import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;

public class CustomTriggerInstance extends AbstractCriterionTriggerInstance {
    private final LinkedList<TriggerMatchCallback> callbacks;

    public CustomTriggerInstance(ResourceLocation id, ContextAwarePredicate player, LinkedList<TriggerMatchCallback> callbacks) {
        super(id, player);
        this.callbacks = callbacks;
    }

    boolean matches(Object[] tests) {
        if (callbacks.size() != tests.length) return false;
        for (int i = 0; i < tests.length; i++) {
            if (!callbacks.get(i).match(tests[i])) return false;
        }
        return true;
    }
}
