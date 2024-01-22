package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.configure.AdvConfigureEvent;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;

import java.nio.file.Path;

public class AdvJS implements ModInitializer {
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvConfigureEvent.class);
    public static final Path EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_example.js");
    public static final Path STORY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_story.js");
    public static final Path ADVENTURE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_adventure.js");
    public static final Path NETHER = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_nether.js");
    public static final Path HUSBANDRY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_husbandry.js");

    @Override
    public void onInitialize() {
        CommonProperties.reload();
        CriteriaTriggers.initialize();
        CommandRegistrationCallback.EVENT.register(AdvCommand::register);
    }
}
