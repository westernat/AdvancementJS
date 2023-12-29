package org.mesdag.advjs.trigger;

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

    public void setBlock(Identifier blockId) {
        this.block = warpBlock(blockId);
    }

    public void setState(StatePredicate state) {
        this.state = state;
    }

    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
