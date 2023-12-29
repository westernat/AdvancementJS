package org.mesdag.advjs.trigger;

import net.minecraft.block.Block;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

class DestroyBlockBuilder extends AbstractTriggerBuilder implements ItemSetter, BlockSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    NumberRange.IntRange bounds = NumberRange.IntRange.ANY;

    public void setBlock(Identifier blockId) {
        this.block = warpBlock(blockId);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    public void setBounds(NumberRange.IntRange bounds) {
        this.bounds = bounds;
    }
}
