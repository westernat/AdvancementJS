package org.mesdag.advjs.mixin;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.RewardsKJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementRewards.class)
public abstract class AdvancementRewardsMixin implements RewardsKJS {
    @Unique
    private @Nullable StatusEffectInstance[] advJS$mobEffectInstances = null;

    @Override
    @HideFromJS
    public void advJS$setMobEffectInstances(StatusEffectInstance[] mobEffectInstances) {
        advJS$mobEffectInstances = mobEffectInstances;
    }

    @Inject(method = "apply", at = @At("TAIL"))
    private void advJS$grant(ServerPlayerEntity serverPlayer, CallbackInfo ci) {
        if (advJS$mobEffectInstances == null) return;

        for (StatusEffectInstance mobEffectInstance : advJS$mobEffectInstances) {
            if (mobEffectInstance != null) {
                serverPlayer.addStatusEffect(new StatusEffectInstance(
                    mobEffectInstance.getEffectType(),
                    mobEffectInstance.getDuration(),
                    mobEffectInstance.getAmplifier(),
                    mobEffectInstance.isAmbient(),
                    mobEffectInstance.shouldShowParticles(),
                    mobEffectInstance.shouldShowIcon()
                ));
            }
        }
    }
}
