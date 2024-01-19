package org.mesdag.advjs.mixin;

import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
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
        TextContent contents = self.getName().getContent();
        String key = "";
        if (contents instanceof TranslatableTextContent translatableContents) {
            key = translatableContents.getKey();
        } else if (contents instanceof LiteralTextContent literalContents) {
            key = literalContents.string();
        }
        CriteriaTriggers.BOSS_EVENT.trigger(serverPlayer, self.shouldDarkenSky(), self.hasDragonMusic(), self.shouldThickenFog(), key);
    }
}