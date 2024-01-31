package org.mesdag.advjs.trigger.registry;

import net.minecraft.util.Identifier;

import java.util.LinkedList;


public class BaseTriggerBuilder {
    final LinkedList<TriggerMatchCallback> callbacks = new LinkedList<>();

    BaseTriggerBuilder(Identifier id) {
        CustomTriggers.BUILDERS.put(id, this);
    }

    public BaseTriggerBuilder match(TriggerMatchCallback callback) {
        callbacks.add(callback);
        return this;
    }
}
