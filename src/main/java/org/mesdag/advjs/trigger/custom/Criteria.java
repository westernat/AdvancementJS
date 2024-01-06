package org.mesdag.advjs.trigger.custom;

import net.minecraft.advancements.CriteriaTriggers;

public class Criteria {
    public static final BlockDestroyedTrigger BLOCK_DESTROYED = CriteriaTriggers.register(new BlockDestroyedTrigger());
    public static final PlayerTouchTrigger PLAYER_TOUCH = CriteriaTriggers.register(new PlayerTouchTrigger());
}
