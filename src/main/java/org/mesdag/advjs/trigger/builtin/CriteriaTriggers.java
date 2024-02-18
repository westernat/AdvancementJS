package org.mesdag.advjs.trigger.builtin;

import net.minecraft.advancement.criterion.Criteria;

public class CriteriaTriggers {
    public static BlockDestroyedCriterion BLOCK_DESTROYED;
    public static PlayerTouchCriterion PLAYER_TOUCH;
    public static BossEventCriterion BOSS_EVENT;
    public static IncreasedKillScoreCriterion INCREASED_KILL_SCORE;

    public static void initialize() {
        BLOCK_DESTROYED = Criteria.register(new BlockDestroyedCriterion());
        PLAYER_TOUCH = Criteria.register(new PlayerTouchCriterion());
        BOSS_EVENT = Criteria.register(new BossEventCriterion());
        INCREASED_KILL_SCORE = Criteria.register(new IncreasedKillScoreCriterion());
    }
}
