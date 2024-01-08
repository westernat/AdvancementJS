package org.mesdag.advjs.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "afterBreak", at = @At("TAIL"))
    private void advJS$trigger(World level, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (!level.isClient && player instanceof ServerPlayerEntity serverPlayer) {
            CriteriaTriggers.BLOCK_DESTROYED.trigger(serverPlayer, state, itemStack);
        }
    }
}
