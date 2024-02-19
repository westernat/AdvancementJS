package org.mesdag.advjs.util;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public interface RewardsKJS {
    default AdvancementRewards advJS$getSelf() {
        return (AdvancementRewards) this;
    }

    void advJS$setMobEffectInstances(List<MobEffectInstance> mobEffectInstance);
}
