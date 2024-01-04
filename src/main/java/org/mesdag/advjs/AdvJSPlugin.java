package org.mesdag.advjs;

import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.world.GameMode;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;
import org.mesdag.advjs.util.StatsWrapper;

public class AdvJSPlugin extends KubeJSPlugin {
    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("TASK", AdvancementFrame.TASK);
        event.add("GOAL", AdvancementFrame.GOAL);
        event.add("CHALLENGE", AdvancementFrame.CHALLENGE);

        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("AND", CriterionMerger.AND);
        event.add("OR", CriterionMerger.OR);

        event.add("Stats", StatsWrapper.class);

        event.add("GameType", GameTypeWrapper.class);
        event.add("SURVIVAL", GameMode.SURVIVAL);
        event.add("CREATIVE", GameMode.CREATIVE);
        event.add("ADVENTURE", GameMode.ADVENTURE);
        event.add("SPECTATOR", GameMode.SPECTATOR);
    }
}
