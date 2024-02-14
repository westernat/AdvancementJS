package org.mesdag.advjs.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Function;

import static org.mesdag.advjs.util.Data.LOCK_ITEM_USE;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(
        method = "onItemUse",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/context/UseOnContext;getClickedPos()Lnet/minecraft/core/BlockPos;"),
        locals = LocalCapture.CAPTURE_FAILSOFT,
        cancellable = true)
    private void advJS$useOn(UseOnContext p_41662_, Function<UseOnContext, InteractionResult> callback, CallbackInfoReturnable<InteractionResult> cir, Player player) {
        if (player instanceof ServerPlayer serverPlayer && !AdvHelper.advDone(serverPlayer, LOCK_ITEM_USE.get(getItem()))) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void advJS$use(Level level, Player player, InteractionHand p_41685_, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (level.isClientSide) {
            if (AdvHelper.clientAdvDone(LOCK_ITEM_USE.get(getItem()))) return;
        } else if (AdvHelper.advDone((ServerPlayer) player, LOCK_ITEM_USE.get(getItem()))) {
            return;
        }
        cir.setReturnValue(InteractionResultHolder.pass((ItemStack) (Object) this));
    }
}
