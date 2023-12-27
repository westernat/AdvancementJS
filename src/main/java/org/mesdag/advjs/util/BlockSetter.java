package org.mesdag.advjs.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface BlockSetter {
    default Block warpBlock(ResourceLocation blockId) {
        return Registry.BLOCK.get(blockId);
    }
}
