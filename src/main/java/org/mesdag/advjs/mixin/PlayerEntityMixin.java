package org.mesdag.advjs.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
    @Inject(method = "collideWithEntity", at = @At("HEAD"))
    private void advJS$touch(Entity entity, CallbackInfo ci) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        if (self instanceof ServerPlayerEntity serverPlayer) {
            CriteriaTriggers.PLAYER_TOUCH.trigger(serverPlayer, entity);
        }
    }
}
