package org.mesdag.advjs.trigger.registry;

import net.minecraft.resources.ResourceLocation;

import java.util.LinkedList;


public class CustomTriggerBuilder {
    final LinkedList<TriggerMatchCallback> callbacks = new LinkedList<>();
    CustomTriggerBuilder(ResourceLocation id) {
        CustomTriggers.BUILDERS.put(id, this);
    }

    public CustomTriggerBuilder match(TriggerMatchCallback callback) {
        callbacks.add(callback);
        return this;
    }
}
