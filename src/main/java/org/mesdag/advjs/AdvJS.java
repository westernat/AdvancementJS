package org.mesdag.advjs;

import com.mojang.logging.LogUtils;
import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;


@Mod(AdvJS.MODID)
public class AdvJS {
    public static final String MODID = "advjs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public AdvJS() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        new AdvCreateEvent().post(ScriptType.STARTUP, "advjs");
        LOGGER.info("AdvJS Loaded!");
    }
}
