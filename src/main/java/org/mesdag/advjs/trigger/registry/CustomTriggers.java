package org.mesdag.advjs.trigger.registry;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.util.Identifier;

import java.util.Hashtable;
import java.util.LinkedList;

public class CustomTriggers {
    @HideFromJS
    static final Hashtable<Identifier, CustomTriggerBuilder> BUILDERS = new Hashtable<>();
    @HideFromJS
    public static final Hashtable<Identifier, CustomTrigger> TRIGGERS = new Hashtable<>();
    public static final CustomTrigger IMPOSSIBLE_TRIGGER = new CustomTrigger(CustomTrigger.IMPOSSIBLE_ID, new LinkedList<>());
    public static final CustomTriggerInstance IMPOSSIBLE = new CustomTriggerInstance(Criteria.IMPOSSIBLE.getId(), LootContextPredicate.EMPTY, new LinkedList<>());

    @HideFromJS
    public static void registerAll() {
        BUILDERS.forEach((key, value) -> {
            TRIGGERS.put(key, Criteria.register(new CustomTrigger(key, value.callbacks)));
            ConsoleJS.STARTUP.info("AdvJS/trigger: Registered trigger " + key);
        });
        BUILDERS.clear();
    }

    public static CustomTrigger of(Identifier id) {
        CustomTrigger trigger = TRIGGERS.get(id);
        if (trigger == null) {
            ConsoleJS.SERVER.error("No such trigger: '%s'".formatted(id));
            return IMPOSSIBLE_TRIGGER;
        }
        return trigger;
    }
}
