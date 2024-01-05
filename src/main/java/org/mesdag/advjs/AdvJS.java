package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;


@Mod("advjs")
public class AdvJS {
    public static final Logger LOGGER = LoggerFactory.getLogger("AdvJS");
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvancementEvent.class);
    public static final Path EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advancement.js");

    public AdvJS() {
        CommonProperties.reload();
    }
}
