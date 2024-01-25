package org.mesdag.advjs.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Inject(method = "updateKilledAdvancementCriterion", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;addScore(I)V"))
    private void advJS$killScore(Entity killed, int score, DamageSource damageSource, CallbackInfo ci) {
        CriteriaTriggers.INCREASED_KILL_SCORE.trigger((ServerPlayerEntity) (Object) this, killed, score, damageSource);
    }
}
