package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.fml.common.Mod;
import org.mesdag.advjs.configure.AdvConfigureEvent;
import org.mesdag.advjs.trigger.custom.Criteria;

import java.nio.file.Path;


@Mod("advjs")
public class AdvJS {
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvConfigureEvent.class);
    public static final Path EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advancement.js");

    public AdvJS() {
        CommonProperties.reload();
        Criteria.initialize();
    }
}
