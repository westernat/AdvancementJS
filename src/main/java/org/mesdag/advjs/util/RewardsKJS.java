package org.mesdag.advjs.util;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.world.effect.MobEffectInstance;

public interface RewardsKJS {
    default AdvancementRewards advJS$getSelf() {
        return (AdvancementRewards) this;
    }

    void advJS$setMobEffectInstances(MobEffectInstance[] mobEffectInstance);
}
