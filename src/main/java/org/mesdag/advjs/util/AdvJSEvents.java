package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraftforge.fml.ModList;
import org.mesdag.advjs.advancement.AdvConfigureEventJS;
import org.mesdag.advjs.advancement.AdvLockEventJS;
import org.mesdag.advjs.integration.betteradvancements.BetterAdvEventJS;
import org.mesdag.advjs.integration.betteradvancements.BetterAdvModifier;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.trigger.custom.TriggerRegistryEventJS;

import java.util.Map;

public interface AdvJSEvents {
    EventGroup GROUP = EventGroup.of("AdvJSEvents");

    EventHandler ADVANCEMENT = GROUP.server("advancement", () -> AdvConfigureEventJS.class);
    EventHandler LOCK = GROUP.server("lock", () -> AdvLockEventJS.class);
    EventHandler TRIGGER = GROUP.startup("trigger", () -> TriggerRegistryEventJS.class);
    EventHandler BETTER_ADV = GROUP.server("betterAdv", () -> BetterAdvEventJS.class);

    static void postAdv(LootDataManager lootData) {
        ADVANCEMENT.post(new AdvConfigureEventJS(new Trigger(lootData)));
    }

    static void postBetter(Map<ResourceLocation, Advancement.Builder> map){
        if (ModList.get().isLoaded("betteradvancements")) {
            BETTER_ADV.post(new BetterAdvEventJS());
            BetterAdvModifier.MODIFIERS.removeIf(modifier -> modifier.modify(map.get(modifier.getId())));
        }
    }
}
