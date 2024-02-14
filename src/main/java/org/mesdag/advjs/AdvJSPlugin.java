package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import org.mesdag.advjs.advancement.AdvLockEventJS;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.trigger.registry.CustomTriggers;
import org.mesdag.advjs.trigger.registry.TriggerRegistryEventJS;
import org.mesdag.advjs.util.*;

import java.nio.file.Files;

public class AdvJSPlugin extends KubeJSPlugin {
    static boolean DEBUG;

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("GameMode", GameTypeWrapper.class);
        event.add("Bounds", Bounds.class);
        event.add("CustomTriggers", CustomTriggers.class);
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.registerSimple(Bounds.class, Bounds::of);
        typeWrappers.registerSimple(AdvancementFilter.class, AdvancementFilter::of);
    }

    @Override
    public void initStartup() {
        AdvJSEvents.TRIGGER.post(new TriggerRegistryEventJS());
        CustomTriggers.registerAll();
    }

    @Override
    public void onServerReload() {
        Data.clear();
        AdvJSEvents.LOCK.post(new AdvLockEventJS());
    }

    @Override
    public void registerEvents() {
        AdvJSEvents.GROUP.register();
    }

    @Override
    public void loadCommonProperties(CommonProperties properties) {
        DEBUG = properties.get("AdvJSDebug", true);
        example(properties.get("AdvJSExample", true));
    }

    private static void example(boolean generate) {
        if (!generate) return;

        if (Files.notExists(AdvJS.STARTUP_EXAMPLE)) {
            try {
                Files.writeString(AdvJS.STARTUP_EXAMPLE, AdvCommand.STARTUP_EXAMPLE);
                ConsoleJS.SERVER.info("AdvJS: Generated advjs_startup.js");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (Files.notExists(AdvJS.SERVER_EXAMPLE)) {
            try {
                Files.writeString(AdvJS.SERVER_EXAMPLE, AdvCommand.SERVER_EXAMPLE);
                ConsoleJS.SERVER.info("AdvJS: Generated advjs_server.js");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
