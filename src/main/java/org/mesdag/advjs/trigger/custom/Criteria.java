package org.mesdag.advjs.trigger.custom;

import net.minecraft.advancements.CriteriaTriggers;

public class Criteria {
    public static BlockDestroyedTrigger BLOCK_DESTROYED;
    public static PlayerTouchTrigger PLAYER_TOUCH;
    public static BossEventTrigger BOSS_EVENT;
    public static IncreasedKillScoreTrigger INCREASED_KILL_SCORE;

    public static void initialize() {
        BLOCK_DESTROYED = CriteriaTriggers.register(new BlockDestroyedTrigger());
        PLAYER_TOUCH = CriteriaTriggers.register(new PlayerTouchTrigger());
        BOSS_EVENT = CriteriaTriggers.register(new BossEventTrigger());
        INCREASED_KILL_SCORE = CriteriaTriggers.register(new IncreasedKillScoreTrigger());
    }
}
