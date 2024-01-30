package org.mesdag.advjs.trigger.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;


public class BaseTriggerBuilder {
    final LinkedList<TriggerMatchCallback> callbacks = new LinkedList<>();

    BaseTriggerBuilder(ResourceLocation id) {
        CustomTriggers.BUILDERS.put(id, this);
    }

    public BaseTriggerBuilder match(TriggerMatchCallback callback) {
        callbacks.add(callback);
        return this;
    }
}
