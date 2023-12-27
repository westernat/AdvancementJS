package org.mesdag.advjs.predicate;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.util.BlockSetter;

class BlockPredicateBuilder implements BlockSetter {
    final BlockPredicate.Builder builder = BlockPredicate.Builder.block();

    public void ofTag(ResourceLocation location) {
        builder.of(TagKey.create(Registry.BLOCK_REGISTRY, location));
    }

    public void ofBlocks(ResourceLocation... blockIds) {
        ImmutableSet.Builder<Block> setBuilder = ImmutableSet.builder();
        for (ResourceLocation blockId : blockIds) {
            setBuilder.add(warpBlock(blockId));
        }
        builder.of(setBuilder.build());
    }

    public void setProperties(StatePropertiesPredicate statePropertiesPredicate) {
        builder.setProperties(statePropertiesPredicate);
    }

    public void hasNbt(CompoundTag nbt) {
        builder.hasNbt(nbt);
    }
}
