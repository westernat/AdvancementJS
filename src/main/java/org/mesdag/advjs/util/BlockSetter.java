package org.mesdag.advjs.util;

import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public interface BlockSetter {
    default Block warpBlock(Identifier blockId) {
        return Registries.BLOCK.get(blockId);
    }
}
