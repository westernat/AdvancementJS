package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class BeeNestDestroyedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    @Nullable
    Block block = null;
    ItemPredicate item = ItemPredicate.ANY;
    MinMaxBounds.Ints bounds = MinMaxBounds.Ints.ANY;

    @Info("Checks the block that was destroyed.")
    public void setBlock(BeeNest block) {
        this.block = block.block;
    }

    @Info("The item used to break the block.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item used to break the block.")
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("The item used to break the block.")
    public void setItem(Ingredient ingredient) {
        this.item = wrapItem(ingredient);
    }

    @Info("The number of bees that were inside the bee nest/beehive before it was broken.")
    public void setBounds(Bounds bounds) {
        this.bounds = bounds.toIntegerBounds();
    }

    public enum BeeNest {
        bee_nest(Blocks.BEE_NEST),
        beehive(Blocks.BEEHIVE);

        final Block block;

        BeeNest(Block block) {
            this.block = block;
        }
    }
}
