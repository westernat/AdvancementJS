package org.mesdag.advjs.mixin;

import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.LiteralContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import org.mesdag.advjs.trigger.custom.Criteria;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerBossEvent.class)
public abstract class ServerBossEventMixin {
    @Inject(
        method = "addPlayer",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;send(Lnet/minecraft/network/protocol/Packet;)V", shift = At.Shift.AFTER))
    private void advJS$trigger(ServerPlayer serverPlayer, CallbackInfo ci) {
        ServerBossEvent self = (ServerBossEvent) (Object) this;
        ComponentContents contents = self.getName().getContents();
        String key = "";
        if (contents instanceof TranslatableContents translatableContents) {
            key = translatableContents.getKey();
        } else if (contents instanceof LiteralContents literalContents) {
            key = literalContents.text();
        }
        Criteria.BOSS_EVENT.trigger(serverPlayer, self.shouldDarkenScreen(), self.shouldPlayBossMusic(), self.shouldCreateWorldFog(), key);
    }
}
