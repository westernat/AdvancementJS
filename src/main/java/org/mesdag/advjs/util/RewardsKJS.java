package org.mesdag.advjs.util;

import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.entity.effect.StatusEffectInstance;

public interface RewardsKJS {
    default AdvancementRewards advJS$getSelf() {
        return (AdvancementRewards) this;
    }

    void advJS$setMobEffectInstances(StatusEffectInstance[] mobEffectInstance);
}
