package org.mesdag.advjs.mixin;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.RewardsKJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(AdvancementRewards.class)
public abstract class AdvancementRewardsMixin implements RewardsKJS {
    @Unique
    private @Nullable List<MobEffectInstance> advJS$mobEffectInstances;

    @Override
    @HideFromJS
    public void advJS$setMobEffectInstances(List<MobEffectInstance> mobEffectInstances) {
        advJS$mobEffectInstances = mobEffectInstances;
    }

    @Inject(method = "grant", at = @At("TAIL"))
    private void advJS$grant(ServerPlayer serverPlayer, CallbackInfo ci) {
        if (advJS$mobEffectInstances == null) return;

        for (MobEffectInstance mobEffectInstance : advJS$mobEffectInstances) {
            if (mobEffectInstance != null) {
                serverPlayer.addEffect(new MobEffectInstance(
                    mobEffectInstance.getEffect(),
                    mobEffectInstance.getDuration(),
                    mobEffectInstance.getAmplifier(),
                    mobEffectInstance.isAmbient(),
                    mobEffectInstance.isVisible(),
                    mobEffectInstance.showIcon()
                ));
            }
        }
    }
}
