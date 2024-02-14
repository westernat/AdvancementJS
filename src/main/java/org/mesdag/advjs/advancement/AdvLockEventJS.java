package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.ItemSetter;

import static org.mesdag.advjs.util.Data.LOCK_RESULT;

public class AdvLockEventJS extends EventJS implements ItemSetter {
    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(Ingredient toLock, Identifier lockBy) {
        LOCK_RESULT.put(wrapItem(toLock), lockBy);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(ItemPredicate toLock, Identifier lockBy) {
        LOCK_RESULT.put(toLock, lockBy);
    }
}
