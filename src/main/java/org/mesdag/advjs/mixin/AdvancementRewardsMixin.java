package org.mesdag.advjs.mixin;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.RewardsAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementRewards.class)
public abstract class AdvancementRewardsMixin implements RewardsAccessor {
    @Nullable
    private StatusEffectInstance[] advJS$mobEffectInstances = null;

    @Override
    @HideFromJS
    public void advJS$setMobEffectInstances(StatusEffectInstance[] mobEffectInstances) {
        advJS$mobEffectInstances = mobEffectInstances;
    }

    @Inject(method = "apply", at = @At("HEAD"))
    private void advJS$grant(ServerPlayerEntity serverPlayer, CallbackInfo ci) {
        if (advJS$mobEffectInstances == null) return;

        for (StatusEffectInstance mobEffectInstance : advJS$mobEffectInstances) {
            if (mobEffectInstance != null) {
                serverPlayer.addStatusEffect(mobEffectInstance);
            }
        }
    }
}
