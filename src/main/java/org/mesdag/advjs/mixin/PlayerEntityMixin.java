package org.mesdag.advjs.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.mesdag.advjs.trigger.builtin.CriteriaTriggers;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.LOCK_ENTITY_INTERACT;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "collideWithEntity", at = @At("HEAD"))
    private void advJS$touch(Entity entity, CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        if (self instanceof ServerPlayerEntity serverPlayer) {
            CriteriaTriggers.PLAYER_TOUCH.trigger(serverPlayer, entity);
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    private void advJS$interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        if (self instanceof ServerPlayerEntity serverPlayer && !AdvHelper.advDone(serverPlayer, LOCK_ENTITY_INTERACT.get(entity.getType()))) {
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
