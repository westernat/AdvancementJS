package org.mesdag.advjs.mixin;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import org.mesdag.advjs.trigger.custom.CriteriaTriggers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerBossBar.class)
public abstract class ServerBossBarMixin {
    @Inject(
        method = "addPlayer",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;sendPacket(Lnet/minecraft/network/packet/Packet;)V", shift = At.Shift.AFTER))
    private void advJS$trigger(ServerPlayerEntity serverPlayer, CallbackInfo ci) {
        ServerBossBar self = (ServerBossBar) (Object) this;
        // TODO displayName
        CriteriaTriggers.BOSS_EVENT.trigger(serverPlayer, self.shouldDarkenSky(), self.hasDragonMusic(), self.shouldThickenFog());
    }
}
