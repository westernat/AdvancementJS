package org.mesdag.advjs;

import com.mojang.logging.LogUtils;
import dev.latvian.mods.kubejs.bindings.event.ServerEvents;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;


@Mod(AdvJS.MODID)
public class AdvJS {
    public static final String MODID = "advjs";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final EventHandler ADVANCEMENT = ServerEvents.GROUP.server("advancement", () -> AdvCreateEvent.class);

    public AdvJS() {
    }
}
