package org.mesdag.advjs.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import org.mesdag.advjs.trigger.builtin.Criteria;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin {
    @Inject(method = "awardKillScore", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;increaseScore(I)V"))
    private void advJS$killScore(Entity killed, int score, DamageSource damageSource, CallbackInfo ci) {
        Criteria.INCREASED_KILL_SCORE.trigger((ServerPlayer) (Object) this, killed, score, damageSource);
    }
}
