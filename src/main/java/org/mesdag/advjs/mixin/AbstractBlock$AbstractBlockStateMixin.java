package org.mesdag.advjs.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.LOCK_BLOCK_INTERACT;

@Mixin(AbstractBlock.AbstractBlockState.class)
public abstract class AbstractBlock$AbstractBlockStateMixin {
    @Shadow public abstract Block getBlock();

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void advJS$pass(World world, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (world.isClient) return;
        if (!AdvHelper.advDone((ServerPlayerEntity) player, LOCK_BLOCK_INTERACT.get(getBlock()))) cir.setReturnValue(ActionResult.PASS);
    }
}
