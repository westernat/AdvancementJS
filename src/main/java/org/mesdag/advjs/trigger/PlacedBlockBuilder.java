package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

import javax.annotation.Nullable;

class PlacedBlockBuilder extends AbstractTriggerBuilder implements BlockSetter, ItemSetter {
    @Nullable
    Block block = null;
    StatePropertiesPredicate state = StatePropertiesPredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The block that placed.")
    public void setBlock(ResourceLocation blockId) {
        this.block = warpBlock(blockId);
    }

    @Info("The block state belongs to that block.")
    public void setState(StatePropertiesPredicate state) {
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
