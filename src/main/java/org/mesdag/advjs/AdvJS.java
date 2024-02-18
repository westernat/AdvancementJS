package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.trigger.builtin.CriteriaTriggers;

import java.nio.file.Path;

public class AdvJS implements ModInitializer {
    public static final Path SERVER_EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_server.js");
    public static final Path STARTUP_EXAMPLE = KubeJSPaths.STARTUP_SCRIPTS.resolve("advjs_startup.js");
    public static final Path CLIENT_EXAMPLE = KubeJSPaths.CLIENT_SCRIPTS.resolve("advjs_client.js");
    public static final Path STORY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_story.js");
    public static final Path ADVENTURE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_adventure.js");
    public static final Path NETHER = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_nether.js");
    public static final Path HUSBANDRY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_husbandry.js");
    public static final Path END = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_end.js");

    public static final Identifier PARENT = new Identifier("advjs", "parent");

    @Override
    public void onInitialize() {
        CommonProperties.reload();
        CriteriaTriggers.initialize();
        CommandRegistrationCallback.EVENT.register(AdvCommand::register);
    }

    public static void debugInfo(String info) {
        if (AdvJSPlugin.DEBUG) {
            ConsoleJS.SERVER.info(info);
        }
    }

    public static boolean betterLoaded() {
        return FabricLoader.getInstance().isModLoaded("betteradvancements");
    }
}
