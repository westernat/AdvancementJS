package org.mesdag.advjs.predicate;

import com.google.common.collect.ImmutableSet;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.BlockSetter;

class BlockPredicateBuilder implements BlockSetter {
    final BlockPredicate.Builder builder = BlockPredicate.Builder.create();

    @Info("Match block's tag.")
    public void ofTag(Identifier location) {
        builder.tag(TagKey.of(RegistryKeys.BLOCK, location));
    }

    @Info("Match all blocks.")
    public void ofBlocks(Identifier... blockIds) {
        ImmutableSet.Builder<Block> setBuilder = ImmutableSet.builder();
        for (Identifier blockId : blockIds) {
            setBuilder.add(warpBlock(blockId));
        }
        builder.blocks(setBuilder.build());
    }

    @Info("Match block state.")
    public void setProperties(StatePredicate statePropertiesPredicate) {
        builder.state(statePropertiesPredicate);
    }

    @Info("Match nbt.")
    public void hasNbt(NbtCompound nbt) {
        builder.nbt(nbt);
    }
}
