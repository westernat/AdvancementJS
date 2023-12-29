package org.mesdag.advjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;
import org.mesdag.advjs.util.StatsWrapper;

public class AdvJSPlugin extends KubeJSPlugin {
    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("Stats", StatsWrapper.class);
        event.add("GameType", GameTypeWrapper.class);
    }

    @Override
    public void initStartup() {
        AdvJS.ADVANCEMENT.post(ScriptType.STARTUP, new AdvCreateEvent());
        AdvJS.LOGGER.info("AdvJS Loaded!");
    }
}
