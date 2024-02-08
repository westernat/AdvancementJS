package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.util.ItemSetter;

import static org.mesdag.advjs.util.Data.LOCK_INTERACT;
import static org.mesdag.advjs.util.Data.LOCK_RESULT;

public class AdvLockEventJS extends EventJS implements ItemSetter {
    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(Ingredient toLock, ResourceLocation lockBy) {
        LOCK_RESULT.put(wrapItem(toLock), lockBy);
    }

    @Info(value = "Lock recipe by advancement. It will only deny player take the result from GUI.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void result(ItemPredicate toLock, ResourceLocation lockBy) {
        LOCK_RESULT.put(toLock, lockBy);
    }

    @Info(value = "Deny player interact with specific block by advancement.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void interact(Block toLock, ResourceLocation lockBy) {
        LOCK_INTERACT.put(toLock, lockBy);
    }
}
