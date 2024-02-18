package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import net.minecraft.loot.LootManager;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.advancement.AdvConfigureEventJS;
import org.mesdag.advjs.advancement.AdvLockEventJS;
import org.mesdag.advjs.integration.betteradvancements.BetterAdvEventJS;
import org.mesdag.advjs.integration.betteradvancements.BetterAdvModifier;
import org.mesdag.advjs.integration.revelationary.AdvRevelationEventJS;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.trigger.custom.TriggerRegistryEventJS;

public interface AdvJSEvents {
    EventGroup GROUP = EventGroup.of("AdvJSEvents");

    EventHandler ADVANCEMENT = GROUP.server("advancement", () -> AdvConfigureEventJS.class);
    EventHandler LOCK = GROUP.server("lock", () -> AdvLockEventJS.class);
    EventHandler TRIGGER = GROUP.startup("trigger", () -> TriggerRegistryEventJS.class);

    EventHandler REVELATION = GROUP.client("revelation", () -> AdvRevelationEventJS.class);
    EventHandler BETTER_ADV = GROUP.server("betterAdv", () -> BetterAdvEventJS.class);

    static void postAdv(LootManager conditionManager) {
        ADVANCEMENT.post(new AdvConfigureEventJS(new Trigger(conditionManager)));
        if (AdvJS.betterLoaded()) {
            BETTER_ADV.post(new BetterAdvEventJS());
            BetterAdvModifier.MODIFIERS.removeIf(BetterAdvModifier::modify);
        }
    }
}
