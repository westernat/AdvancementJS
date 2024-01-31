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
    static final Hashtable<Identifier, BaseTriggerBuilder> BUILDERS = new Hashtable<>();
    @HideFromJS
    public static final Hashtable<Identifier, BaseCriterion> TRIGGERS = new Hashtable<>();
    public static final BaseTriggerInstance IMPOSSIBLE = new BaseTriggerInstance(Criteria.IMPOSSIBLE.getId(), LootContextPredicate.EMPTY, new LinkedList<>());

    @HideFromJS
    public static void registerAll() {
        BUILDERS.forEach((key, value) -> {
            TRIGGERS.put(key, Criteria.register(new BaseCriterion(key, value.callbacks)));
            ConsoleJS.STARTUP.info("AdvJS/trigger: Registered trigger " + key);
        });
        BUILDERS.clear();
    }

    public static BaseCriterion of(Identifier id) {
        return TRIGGERS.get(id);
    }
}
