package org.mesdag.advjs.trigger.registry;

import net.minecraft.util.Identifier;

import java.util.LinkedList;


public class CustomTriggerBuilder {
    final LinkedList<TriggerMatchCallback> callbacks = new LinkedList<>();

    CustomTriggerBuilder(Identifier id) {
        CustomTriggers.BUILDERS.put(id, this);
    }

    public CustomTriggerBuilder match(TriggerMatchCallback callback) {
        callbacks.add(callback);
        return this;
    }
}
