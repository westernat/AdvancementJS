package org.mesdag.advjs.integration.betteradvancements;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.util.Identifier;

public class BetterAdvEventJS extends EventJS {
    public BetterAdvModifier modify(Identifier id){
        return new BetterAdvModifier(id);
    }
}
