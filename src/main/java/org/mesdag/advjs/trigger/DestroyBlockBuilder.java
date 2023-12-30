package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

class DestroyBlockBuilder extends AbstractTriggerBuilder implements ItemSetter, BlockSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints bounds = MinMaxBounds.Ints.ANY;

    public void setBlock(ResourceLocation blockId) {
        this.block = warpBlock(blockId);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    public void setBounds(MinMaxBounds.Ints bounds) {
        this.bounds = bounds;
    }
}
