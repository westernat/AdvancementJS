package org.mesdag.advjs.predicate;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.mesdag.advjs.util.BlockSetter;

class BlockPredicateBuilder implements BlockSetter {
    final BlockPredicate.Builder builder = BlockPredicate.Builder.create();

    public void ofTag(Identifier location) {
        builder.tag(TagKey.of(Registry.BLOCK_KEY, location));
    }

    public void ofBlocks(Identifier... blockIds) {
        ImmutableSet.Builder<Block> setBuilder = ImmutableSet.builder();
        for (Identifier blockId : blockIds) {
            setBuilder.add(warpBlock(blockId));
        }
        builder.blocks(setBuilder.build());
    }

    public void setProperties(StatePredicate statePropertiesPredicate) {
        builder.state(statePropertiesPredicate);
    }

    public void hasNbt(NbtCompound nbt) {
        builder.nbt(nbt);
    }
}
