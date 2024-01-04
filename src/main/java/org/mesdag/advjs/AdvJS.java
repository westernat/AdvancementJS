package org.mesdag.advjs;

import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Mod(AdvJS.MODID)
public class AdvJS {
    public static final String MODID = "advjs";
    public static final Logger LOGGER = LoggerFactory.getLogger("AdvJS");
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvCreateEvent.class);

    public AdvJS() {
    }
}
