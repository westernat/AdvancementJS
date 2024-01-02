package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

class BeeNestDestroyedBuilder extends AbstractTriggerBuilder implements ItemSetter, BlockSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints bounds = MinMaxBounds.Ints.ANY;

    @Info("Checks the block that was destroyed.")
    public void setBlock(ResourceLocation blockId) {
        this.block = warpBlock(blockId);
    }

    @Info("The item used to break the block.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item used to break the block.")
    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    @Info("The number of bees that were inside the bee nest/beehive before it was broken.")
    public void setBounds(MinMaxBounds.Ints bounds) {
        this.bounds = bounds;
    }
}
