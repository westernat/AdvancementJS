package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.*;

public class AdvLockEventJS extends EventJS implements ItemSetter {
    @Info("Deny player take specific result. It only available in GUI.")
    public void result(Ingredient toLock, Identifier lockBy) {
        LOCK_RESULT.put(wrapItem(toLock), lockBy);
    }

    @Info("Deny player take specific result. It only available in GUI.")
    public void result(Consumer<ItemPredicateBuilder> consumer, Identifier lockBy) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        LOCK_RESULT.put(builder.build(), lockBy);
    }

    @Info("Pass item use or use on block.")
    public void itemUse(Item toLock, Identifier lockBy) {
        LOCK_ITEM_USE.put(toLock, lockBy);
    }

    @Info("Deny player interact with specific block.")
    public void blockInteract(Block toLock, Identifier lockBy) {
        LOCK_BLOCK_INTERACT.put(toLock, lockBy);
    }

    @Info("pass player interact with specific type of entities.")
    public void entityInteract(EntityType<?> toLock, Identifier lockBy) {
        LOCK_ENTITY_INTERACT.put(toLock, lockBy);
    }
}
