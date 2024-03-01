package org.mesdag.advjs;

import dev.latvian.mods.kubejs.KubeJSPaths;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.common.Mod;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.event.LockEvent;
import org.mesdag.advjs.trigger.builtin.Criteria;

import java.nio.file.Path;


@Mod("advjs")
public class AdvJS {
    public static final Path SERVER_EXAMPLE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_server.js");
    public static final Path STARTUP_EXAMPLE = KubeJSPaths.STARTUP_SCRIPTS.resolve("advjs_startup.js");
    public static final Path STORY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_story.js");
    public static final Path ADVENTURE = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_adventure.js");
    public static final Path NETHER = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_nether.js");
    public static final Path HUSBANDRY = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_husbandry.js");
    public static final Path END = KubeJSPaths.SERVER_SCRIPTS.resolve("advjs_end.js");

    public static final ResourceLocation PARENT = new ResourceLocation("advjs", "parent");
    public static final Component EMPTY_COMPONENT = Component.empty();

    public AdvJS() {
        Criteria.initialize();
        MinecraftForge.EVENT_BUS.register(LockEvent.class);
        MinecraftForge.EVENT_BUS.addListener(this::registerCommand);
    }

    private void registerCommand(RegisterCommandsEvent event) {
        AdvCommand.register(event.getDispatcher());
    }

    public static void debugInfo(String info) {
        if (AdvJSPlugin.DEBUG) {
            ConsoleJS.SERVER.info(info);
        }
    }
}
