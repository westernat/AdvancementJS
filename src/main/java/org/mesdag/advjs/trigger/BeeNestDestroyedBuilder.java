package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.ItemSetter;

class BeeNestDestroyedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints bounds = MinMaxBounds.Ints.ANY;

    @Info("Checks the block that was destroyed.")
    public void setBlock(@Nullable Block block) {
        this.block = block;
    }

    @Info("The item used to break the block.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item used to break the block.")
    public void setItem(Ingredient ingredient){
        this.item = wrapItem(ingredient);
    }

    @Info("The number of bees that were inside the bee nest/beehive before it was broken.")
    public void setBounds(Bounds bounds) {
        this.bounds = bounds.toIntegerBounds();
    }
}
