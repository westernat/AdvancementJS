package org.mesdag.advjs;

import dev.architectury.event.events.common.LifecycleEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdvJS implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("advjs");

	@Override
	public void onInitialize() {
		LifecycleEvent.SETUP.register(AdvJS::setUp);
	}

	private static void setUp(){
		new AdvCreateEvent().post(ScriptType.STARTUP, "advjs");
		LOGGER.info("AdvJS Loaded!");
	}
}