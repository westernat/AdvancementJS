package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.util.BlockSetter;

import javax.annotation.Nullable;

class SingleBlockBuilder extends AbstractTriggerBuilder implements BlockSetter {
    @Nullable
    Block block = null;
    StatePropertiesPredicate state = StatePropertiesPredicate.ANY;

    public void setBlock(ResourceLocation blockId) {
        this.block = warpBlock(blockId);
    }

    public void setState(StatePropertiesPredicate state) {
        this.state = state;
    }
}
