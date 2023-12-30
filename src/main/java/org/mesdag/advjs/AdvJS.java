package org.mesdag.advjs;

import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvJS implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("advjs");
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvCreateEvent.class);

    @Override
    public void onInitialize() {
    }
}