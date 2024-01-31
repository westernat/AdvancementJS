package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.AdvRemoveFilter;
import org.mesdag.advjs.util.Condition;
import org.mesdag.advjs.util.ItemSetter;
import org.mesdag.advjs.util.Provider;

import static org.mesdag.advjs.util.Data.FILTERS;
import static org.mesdag.advjs.util.Data.LOCK_MAP;

public class AdvConfigureEventJS extends EventJS implements ItemSetter {
    @Info("""
        Trigger required in advancement.
        
        More details please goto https://minecraft.wiki/w/Advancement/JSON_format
        """)
    public final Trigger TRIGGER;
    @Info("Predicate required in trigger.")
    public final Predicate PREDICATE = new Predicate();
    @Info("Conditions used in trigger or predicate.")
    public final Condition CONDITION = new Condition();
    @Info("Provides several data of vanilla.")
    public final Provider PROVIDER = new Provider();

    @HideFromJS
    public AdvConfigureEventJS(Trigger trigger) {
        this.TRIGGER = trigger;
    }

    @Info("Create a new advancement root")
    public AdvBuilder create(Identifier rootPath) {
        return new AdvBuilder(null, "root", rootPath, rootPath.getNamespace().equals("minecraft") ? AdvBuilder.WarnType.NO_SPACE : AdvBuilder.WarnType.NONE);
    }

    @Info("""
        It will automatically remove all of its children.

        If you put in a string, it will remove advancement by its id.

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
    public void lock(Ingredient toLock, Identifier lockBy) {
        LOCK_MAP.put(wrapItem(toLock), lockBy);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void lock(ItemPredicate toLock, AdvBuilder lockBy) {
        LOCK_MAP.put(toLock, lockBy.getId());
    }
}
