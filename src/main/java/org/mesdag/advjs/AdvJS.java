package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.configure.AdvConfigureEvent;
import org.mesdag.advjs.trigger.custom.Criteria;

import java.nio.file.Path;


@Mod("advjs")
public class AdvJS {
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvConfigureEvent.class);
    public static final Path EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_example.js");
    public static final Path STORY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_story.js");
    public static final Path ADVENTURE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_adventure.js");
    public static final Path NETHER = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_nether.js");

    public AdvJS() {
        CommonProperties.reload();
        Criteria.initialize();
        MinecraftForge.EVENT_BUS.addListener(this::registerCommand);
    }

    private void registerCommand(RegisterCommandsEvent event) {
        AdvCommand.register(event.getDispatcher());
    }
}
