package org.mesdag.advjs.trigger;


import net.minecraft.block.Block;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.BlockSetter;


class SingleBlockBuilder extends AbstractTriggerBuilder implements BlockSetter {
    @Nullable
    Block block = null;
    StatePredicate state = StatePredicate.ANY;

    public void setBlock(Identifier blockId) {
        this.block = warpBlock(blockId);
    }

    public void setState(StatePredicate state) {
        this.state = state;
    }
}
