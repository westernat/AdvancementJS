package org.mesdag.advjs.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.mesdag.advjs.trigger.custom.Criteria;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.LOCK_ENTITY_INTERACT;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "touch", at = @At("HEAD"))
    private void advJS$touch(Entity entity, CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self instanceof ServerPlayer serverPlayer) {
            Criteria.PLAYER_TOUCH.trigger(serverPlayer, entity);
        }
    }

    @Inject(method = "interactOn", at = @At("HEAD"), cancellable = true)
    private void advJS$interact(Entity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        Player self = (Player) (Object) this;
        if (self instanceof ServerPlayer serverPlayer && !AdvHelper.advDone(serverPlayer, LOCK_ENTITY_INTERACT.get(entity.getType()))) {
            cir.setReturnValue(InteractionResult.PASS);
        }
    }
}
