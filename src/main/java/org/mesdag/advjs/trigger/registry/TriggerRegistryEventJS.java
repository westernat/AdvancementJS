package org.mesdag.advjs.trigger.registry;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import net.minecraft.resources.ResourceLocation;

public class TriggerRegistryEventJS extends StartupEventJS {
    public BaseTriggerBuilder create(ResourceLocation id){
        return new BaseTriggerBuilder(id);
    }
}
