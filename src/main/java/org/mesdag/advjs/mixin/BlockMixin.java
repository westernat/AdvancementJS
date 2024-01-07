package org.mesdag.advjs.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.mesdag.advjs.trigger.custom.Criteria;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Inject(method = "playerDestroy", at = @At("TAIL"))
    private void advJS$trigger(Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack itemStack, CallbackInfo ci) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            Criteria.BLOCK_DESTROYED.trigger(serverPlayer, state, itemStack);
        }
    }
}
