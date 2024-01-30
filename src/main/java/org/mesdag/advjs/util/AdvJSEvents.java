package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import org.mesdag.advjs.advancement.AdvConfigureEventJS;
import org.mesdag.advjs.advancement.AdvLockEventJS;
import org.mesdag.advjs.integration.revelationary.ClientAdvancementEventJS;
import org.mesdag.advjs.trigger.registry.TriggerRegistryEventJS;

public interface AdvJSEvents {
    EventGroup GROUP = EventGroup.of("AdvJSEvents");

    EventHandler ADVANCEMENT = GROUP.server("advancement", () -> AdvConfigureEventJS.class);
    EventHandler LOCK = GROUP.server("lock", () -> AdvLockEventJS.class);
    EventHandler TRIGGER = GROUP.startup("trigger", () -> TriggerRegistryEventJS.class);

    EventHandler REVELATION = GROUP.client("revelation", () -> ClientAdvancementEventJS.class);
}
