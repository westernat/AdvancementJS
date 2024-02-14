package org.mesdag.advjs.trigger.registry;

import dev.latvian.mods.kubejs.event.StartupEventJS;
import net.minecraft.util.Identifier;

public class TriggerRegistryEventJS extends StartupEventJS {
    public CustomTriggerBuilder create(Identifier id){
        return new CustomTriggerBuilder(id);
    }
}
