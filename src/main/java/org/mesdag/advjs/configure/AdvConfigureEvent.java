package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.AdvRemoveFilter;
import org.mesdag.advjs.util.Condition;
import org.mesdag.advjs.util.Provider;

import static org.mesdag.advjs.util.Data.FILTERS;
import static org.mesdag.advjs.util.Data.LOCK_MAP;

public class AdvConfigureEvent extends EventJS {
    @Info("""
        Trigger required in advancement.
        
        More details please goto https://minecraft.wiki/w/Advancement/JSON_format
        """)
    public final Trigger TRIGGER = new Trigger();
    @Info("Predicate required in trigger.")
    public final Predicate PREDICATE = new Predicate();
    @Info("Conditions used in trigger or predicate.")
    public final Condition CONDITION = new Condition();
    @Info("Provides several data of vanilla.")
    public final Provider PROVIDER = new Provider();

    @Info("Create a new advancement root")
    public AdvBuilder create(Identifier rootPath) {
        if (rootPath.getNamespace().equals("minecraft")) {
            new AdvBuilder(null, "root", rootPath, AdvBuilder.WarnType.NO_SPACE);
        }
        return new AdvBuilder(null, "root", rootPath, AdvBuilder.WarnType.NONE);
    }

    @Info("""
        It will automatically remove all of its children.

        If you put in a string, it will remove advancement by its path.

        Else if you put in a json object, it will remove advancement by filter:

            modid: the mod id of advancement.
            icon: the id of icon item/block.
            frame: type of frame for the icon. Available value is 'challenge', 'goal' or 'task'.
            parent: the parent advancement path of this advancement.
        """)
    public void remove(AdvRemoveFilter filter) {
        if (filter.fail()) {
            ConsoleJS.SERVER.warn("AdvJS/remove: Failed create a filter");
        } else {
            FILTERS.add(filter);
        }
    }

    @Info("Get an exist advancement to modify.")
    public AdvGetter get(Identifier path) {
        return new AdvGetter(path);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void lock(ItemStack toLock, Identifier lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void lock(ItemStack toLock, AdvBuilder lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy.getId());
    }
}
