package org.mesdag.advjs.mixin.revelationary;

import net.minecraft.network.protocol.game.ClientboundUpdateAdvancementsPacket;
import org.mesdag.advjs.integration.revelationary.AdvRevelationEventJS;
import org.mesdag.advjs.util.AdvJSEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "de/dafuqs/revelationary/ClientAdvancements")
public abstract class ClientAdvancementsMixin {
    @Inject(method = "onClientPacket", at = @At("HEAD"))
    private static void advJS$postEvent(ClientboundUpdateAdvancementsPacket packet, CallbackInfo ci) {
        AdvJSEvents.REVELATION.post(AdvRevelationEventJS.INSTANCE);
    }
}
