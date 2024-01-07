package org.mesdag.advjs.mixin;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.RewardsAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AdvancementRewards.class)
public abstract class AdvancementRewardsMixin implements RewardsAccessor {
    @Nullable
    private MobEffectInstance[] advJS$mobEffectInstances = null;

    @Override
    @HideFromJS
    public void advJS$setMobEffectInstances(MobEffectInstance[] mobEffectInstances) {
        advJS$mobEffectInstances = mobEffectInstances;
    }

    @Inject(method = "grant", at = @At("HEAD"))
    private void advJS$grant(ServerPlayer serverPlayer, CallbackInfo ci) {
        if (advJS$mobEffectInstances != null) {
            for (MobEffectInstance mobEffectInstance : advJS$mobEffectInstances) {
                if (mobEffectInstance != null) {
                    serverPlayer.addEffect(mobEffectInstance);
                }
            }
        }
    }
}
