package org.mesdag.advjs.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.phys.BlockHitResult;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.LOCK_BLOCK_INTERACT;

@Mixin(BlockBehaviour.BlockStateBase.class)
public abstract class BlockBehavior$BlockStateBaseMixin {
    @Shadow public abstract Block getBlock();

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void advJS$pass(Level level, Player player, InteractionHand hand, BlockHitResult result, CallbackInfoReturnable<InteractionResult> cir) {
        if (level.isClientSide) return;
        if (!AdvHelper.advDone((ServerPlayer) player, LOCK_BLOCK_INTERACT.get(getBlock()))) cir.setReturnValue(InteractionResult.PASS);
    }
}
