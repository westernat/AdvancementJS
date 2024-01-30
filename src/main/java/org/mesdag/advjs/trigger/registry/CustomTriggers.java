package org.mesdag.advjs.trigger.registry;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;

import java.util.Hashtable;
import java.util.LinkedList;

public class CustomTriggers {
    @HideFromJS
    static final Hashtable<ResourceLocation, BaseTriggerBuilder> BUILDERS = new Hashtable<>();
    @HideFromJS
    public static final Hashtable<ResourceLocation, BaseTrigger> TRIGGERS = new Hashtable<>();
    public static final BaseTriggerInstance IMPOSSIBLE = new BaseTriggerInstance(CriteriaTriggers.IMPOSSIBLE.getId(), ContextAwarePredicate.ANY, new LinkedList<>());

    @HideFromJS
    public static void registerAll() {
        BUILDERS.forEach((key, value) -> {
            TRIGGERS.put(key, CriteriaTriggers.register(new BaseTrigger(key, value.callbacks)));
            ConsoleJS.STARTUP.info("AdvJS/trigger: Registered trigger " + key);
        });
        BUILDERS.clear();
    }

    public static BaseTrigger of(ResourceLocation id) {
        return TRIGGERS.get(id);
    }
}
