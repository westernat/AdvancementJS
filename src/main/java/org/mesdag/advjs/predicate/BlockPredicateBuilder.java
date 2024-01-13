package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class BlockPredicateBuilder {
    final BlockPredicate.Builder builder = BlockPredicate.Builder.block();

    @Info("Match block's tag.")
    public void ofTag(ResourceLocation location) {
        builder.of(TagKey.create(Registries.BLOCK, location));
    }

    @Info("Match all blocks.")
    public void ofBlocks(Block... blocks) {
        builder.of(blocks);
    }

    @Info("Match block state.")
    public void setProperties(StatePropertiesPredicate statePropertiesPredicate) {
        builder.setProperties(statePropertiesPredicate);
    }

    @Info("Match block state.")
    public void setProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder1 = new StatePropertiesPredicateBuilder();
        consumer.accept(builder1);
        builder.setProperties(builder1.build());
    }

    @Info("Match nbt.")
    public void hasNbt(CompoundTag nbt) {
        builder.hasNbt(nbt);
    }

    BlockPredicate build() {
        return builder.build();
    }
}
