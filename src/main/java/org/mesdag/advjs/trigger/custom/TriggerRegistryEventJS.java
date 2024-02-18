package org.mesdag.advjs.trigger.custom;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import net.minecraft.resources.ResourceLocation;

public class TriggerRegistryEventJS extends StartupEventJS {
    public CustomTriggerBuilder create(ResourceLocation id){
        return new CustomTriggerBuilder(id);
    }
}
