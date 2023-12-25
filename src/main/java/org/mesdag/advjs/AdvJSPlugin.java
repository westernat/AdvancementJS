package org.mesdag.advjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.advancements.FrameType;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;

public class AdvJSPlugin extends KubeJSPlugin {
    @Override
    public void addBindings(BindingsEvent event) {
        event.add("FrameType", FrameType.class);
        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
    }
}
