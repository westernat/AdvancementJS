package org.mesdag.advjs.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.mesdag.advjs.trigger.custom.Criteria;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "touch", at = @At("HEAD"))
    private void advJS$touch(Entity entity, CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self instanceof ServerPlayer serverPlayer) {
            Criteria.PLAYER_TOUCH.trigger(serverPlayer, entity);
        }
    }
}
