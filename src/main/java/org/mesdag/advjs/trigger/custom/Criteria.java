package org.mesdag.advjs.trigger.custom;

import net.minecraft.advancements.CriteriaTriggers;

public class Criteria {
    public static BlockDestroyedTrigger BLOCK_DESTROYED;
    public static PlayerTouchTrigger PLAYER_TOUCH;

    public static void initialize() {
        BLOCK_DESTROYED = CriteriaTriggers.register(new BlockDestroyedTrigger());
        PLAYER_TOUCH = CriteriaTriggers.register(new PlayerTouchTrigger());
    }
}
