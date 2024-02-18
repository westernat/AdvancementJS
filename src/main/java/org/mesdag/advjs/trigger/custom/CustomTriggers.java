package org.mesdag.advjs.trigger.custom;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.resources.ResourceLocation;

import java.util.Hashtable;
import java.util.LinkedList;

public class CustomTriggers {
    @HideFromJS
    static final Hashtable<ResourceLocation, CustomTriggerBuilder> BUILDERS = new Hashtable<>();
    @HideFromJS
    public static final Hashtable<ResourceLocation, CustomTrigger> TRIGGERS = new Hashtable<>();
    public static final CustomTrigger IMPOSSIBLE_TRIGGER = new CustomTrigger(CustomTrigger.IMPOSSIBLE_ID, new LinkedList<>());
    public static final CustomTriggerInstance IMPOSSIBLE = new CustomTriggerInstance(CustomTrigger.IMPOSSIBLE_ID, ContextAwarePredicate.ANY, new LinkedList<>());

    @HideFromJS
    public static void registerAll() {
        BUILDERS.forEach((key, value) -> {
            TRIGGERS.put(key, CriteriaTriggers.register(new CustomTrigger(key, value.callbacks)));
            ConsoleJS.STARTUP.info("AdvJS/trigger: Registered trigger " + key);
        });
        BUILDERS.clear();
    }

    public static CustomTrigger of(ResourceLocation id) {
        CustomTrigger trigger = TRIGGERS.get(id);
        if (trigger == null) {
            ConsoleJS.SERVER.error("No such trigger: '%s'".formatted(id));
            return IMPOSSIBLE_TRIGGER;
        }
        return trigger;
    }
}
