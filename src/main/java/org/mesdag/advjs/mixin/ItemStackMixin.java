package org.mesdag.advjs.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static org.mesdag.advjs.util.Data.LOCK_ITEM_USE;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract Item getItem();

    @Inject(
        method = "useOnBlock",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemUsageContext;getBlockPos()Lnet/minecraft/util/math/BlockPos;"),
        locals = LocalCapture.CAPTURE_FAILSOFT,
        cancellable = true)
    private void advJS$useOn(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir, PlayerEntity player) {
        if (player instanceof ServerPlayerEntity serverPlayer && !AdvHelper.advDone(serverPlayer, LOCK_ITEM_USE.get(getItem()))) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }

    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void advJS$use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (world.isClient) {
            if (AdvHelper.clientAdvDone(LOCK_ITEM_USE.get(getItem()))) return;
        } else if (AdvHelper.advDone((ServerPlayerEntity) user, LOCK_ITEM_USE.get(getItem()))) {
            return;
        }
        cir.setReturnValue(TypedActionResult.pass((ItemStack) (Object) this));
    }
}
