package org.mesdag.advjs.predicate;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class BlockPredicateBuilder {
    private final BlockPredicate.Builder builder = BlockPredicate.Builder.block();

    public BlockPredicateBuilder tag(ResourceLocation location) {
        builder.of(TagKey.create(Registry.BLOCK_REGISTRY, location));
        return this;
    }

    public BlockPredicateBuilder blocks(ResourceLocation... blockIds) {
        ImmutableSet.Builder<Block> setBuilder = ImmutableSet.builder();
        for (ResourceLocation blockId : blockIds) {
            setBuilder.add(Registry.BLOCK.get(blockId));
        }
        builder.of(setBuilder.build());
        return this;
    }

    public BlockPredicateBuilder properties(StatePropertiesPredicate statePropertiesPredicate) {
        builder.setProperties(statePropertiesPredicate);
        return this;
    }

    public BlockPredicateBuilder nbt(CompoundTag nbt) {
        builder.hasNbt(nbt);
        return this;
    }

    public BlockPredicate build() {
        return builder.build();
    }
}
