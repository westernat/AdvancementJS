package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import org.mesdag.advjs.advancement.AdvLockEventJS;
import org.mesdag.advjs.command.AdvCommand;
import org.mesdag.advjs.trigger.custom.CustomTriggers;
import org.mesdag.advjs.trigger.custom.TriggerRegistryEventJS;
import org.mesdag.advjs.util.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class AdvJSPlugin extends KubeJSPlugin {
    static boolean DEBUG = false;

    @Override
    public void registerBindings(BindingsEvent event) {
        if (event.getType().isServer()) {
            event.add("FrameType", FrameTypeWrapper.class);
            event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
            event.add("GameMode", GameTypeWrapper.class);
            event.add("Bounds", Bounds.class);
            event.add("CustomTriggers", CustomTriggers.class);
            event.add("AdvHelper", AdvHelper.class);
        }
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
        Data.clearResistance();
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

    private static void example(boolean shouldGen) {
        if (!shouldGen) return;
        generate(AdvJS.SERVER_EXAMPLE, AdvCommand.SERVER_EXAMPLE);
        generate(AdvJS.STARTUP_EXAMPLE, AdvCommand.STARTUP_EXAMPLE);
        generate(AdvJS.CLIENT_EXAMPLE, AdvCommand.CLIENT_EXAMPLE);
    }

    private static void generate(Path file, String text) {
        if (Files.notExists(file)) {
            try {
                Files.writeString(file, text);
                ConsoleJS.SERVER.info("AdvJS: Generated " + file.getFileName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
