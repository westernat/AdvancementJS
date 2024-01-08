package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.block.Block;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

class PlacedBlockBuilder extends AbstractTriggerBuilder implements BlockSetter, ItemSetter {
    @Nullable
    Block block = null;
    StatePredicate state = StatePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The block that placed.")
    public void setBlock(Identifier blockId) {
        this.block = warpBlock(blockId);
    }

    @Info("The block state belongs to that block.")
    public void setState(StatePredicate state) {
        this.state = state;
    }

    @Info("The location of the block placed.")
    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    @Info("The tool is the item used to place the block.")
    public void setTool(ItemPredicate item) {
        this.item = item;
    }

    @Info("The tool is the item used to place the block.")
    public void setTool(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }
}
