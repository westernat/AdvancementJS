package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.fabricmc.api.ModInitializer;
import org.mesdag.advjs.configure.AdvConfigureEvent;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;

import java.nio.file.Path;

public class AdvJS implements ModInitializer {
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvConfigureEvent.class);
    public static final Path EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advancement.js");

    @Override
    public void onInitialize() {
        CommonProperties.reload();
        CriteriaTriggers.initialize();
    }
}