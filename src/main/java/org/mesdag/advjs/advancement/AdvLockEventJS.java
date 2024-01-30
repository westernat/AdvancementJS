package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

import static org.mesdag.advjs.util.Data.LOCK_MAP;

public class AdvLockEventJS extends EventJS implements ItemSetter {
    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(Ingredient toLock, ResourceLocation lockBy) {
        LOCK_MAP.put(wrapItem(toLock), lockBy);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(ItemPredicate toLock, ResourceLocation lockBy) {
        LOCK_MAP.put(toLock, lockBy);
    }
}
