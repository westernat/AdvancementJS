package org.mesdag.advjs.util;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public interface BlockSetter {
    default Block warpBlock(Identifier blockId) {
        return Registry.BLOCK.get(blockId);
    }
}
