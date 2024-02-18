package org.mesdag.advjs.integration.betteradvancements;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;

public class BetterAdvEventJS extends EventJS {
    public BetterAdvModifier modify(ResourceLocation id){
        return new BetterAdvModifier(id);
    }
}
