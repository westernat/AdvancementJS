package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;

import java.nio.file.Files;

import static org.mesdag.advjs.configure.Data.*;

public class AdvJSPlugin extends KubeJSPlugin {
    public static boolean DEBUG;

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("GameType", GameTypeWrapper.class);
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.registerSimple(Bounds.class, Bounds::of);
    }

    @Override
    public void onServerReload() {
        FILTERS.clear();
        GETTER_MAP.clear();
        BUILDER_MAP.clear();
        LOCK_MAP.clear();
        REQUIRE_DONE.clear();
        DISPLAY_OFFSET.clear();
    }

    @Override
    public void loadCommonProperties(CommonProperties properties) {
        DEBUG = properties.get("AdvJSDebug", true);
        example(properties.get("AdvJSExample", true));
    }

    private static void example(boolean generate) {
        if (generate && Files.notExists(AdvJS.EXAMPLE)) {
            try {
                Files.writeString(AdvJS.EXAMPLE, AdvCommand.EXAMPLE);
                ConsoleJS.SERVER.info("AdvJS: Generated advjs_example.js");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
