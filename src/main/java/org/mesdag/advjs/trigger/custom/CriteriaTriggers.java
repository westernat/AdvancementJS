package org.mesdag.advjs.trigger.custom;

import net.minecraft.advancement.criterion.Criteria;

public class CriteriaTriggers {
    public static BlockDestroyedCriterion BLOCK_DESTROYED;
    public static PlayerTouchConditions PLAYER_TOUCH;
    public static BossEventConditions BOSS_EVENT;

    public static void initialize() {
        BLOCK_DESTROYED = Criteria.register(new BlockDestroyedCriterion());
        PLAYER_TOUCH = Criteria.register(new PlayerTouchConditions());
        BOSS_EVENT = Criteria.register(new BossEventConditions());
    }
}
