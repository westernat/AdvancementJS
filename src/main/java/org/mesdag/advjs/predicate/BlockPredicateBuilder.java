package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.block.Block;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class BlockPredicateBuilder {
    final BlockPredicate.Builder builder = BlockPredicate.Builder.create();

    @Info("Match block's tag.")
    public void ofTag(Identifier location) {
        builder.tag(TagKey.of(RegistryKeys.BLOCK, location));
    }

    @Info("Match all blocks.")
    public void ofBlocks(Block... blocks) {
        builder.blocks(blocks);
    }

    @Info("Match block state.")
    public void setProperties(StatePredicate statePropertiesPredicate) {
        builder.state(statePropertiesPredicate);
    }

    @Info("Match block state.")
    public void setProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder1 = new StatePropertiesPredicateBuilder();
        consumer.accept(builder1);
        builder.state(builder1.build());
    }

    @Info("Match nbt.")
    public void hasNbt(NbtCompound nbt) {
        builder.nbt(nbt);
    }

    BlockPredicate build() {
        return builder.build();
    }
}
