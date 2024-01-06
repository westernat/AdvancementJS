package org.mesdag.advjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.world.level.GameType;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;
import org.mesdag.advjs.util.StatsWrapper;

public class AdvJSPlugin extends KubeJSPlugin {
    @Override
    public void addBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("TASK", FrameType.TASK);
        event.add("GOAL", FrameType.GOAL);
        event.add("CHALLENGE", FrameType.CHALLENGE);

        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("AND", RequirementsStrategy.AND);
        event.add("OR", RequirementsStrategy.OR);

        event.add("Stats", StatsWrapper.class);

        event.add("GameType", GameTypeWrapper.class);
        event.add("SURVIVAL", GameType.SURVIVAL);
        event.add("CREATIVE", GameType.CREATIVE);
        event.add("ADVENTURE", GameType.ADVENTURE);
        event.add("SPECTATOR", GameType.SPECTATOR);
    }
}
