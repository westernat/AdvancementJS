package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.block.Block;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

class BeeNestDestroyedBuiilder extends AbstractTriggerBuilder implements ItemSetter, BlockSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    NumberRange.IntRange bounds = NumberRange.IntRange.ANY;

    public void setBlock(Identifier blockId) {
        this.block = warpBlock(blockId);
    }

    public void setTool(ItemPredicate item) {
        this.item = item;
    }

    public void setTool(IngredientJS ingredientJS){
        this.item = warpItem(ingredientJS);
    }

    public void setBounds(NumberRange.IntRange bounds) {
        this.bounds = bounds;
    }
}
