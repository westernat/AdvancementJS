package org.mesdag.advjs.util;

import net.minecraft.world.level.GameType;

public interface GameTypeWrapper {
    GameType SURVIVAL = GameType.SURVIVAL;
    GameType CREATIVE = GameType.CREATIVE;
    GameType ADVENTURE = GameType.ADVENTURE;
    GameType SPECTATOR = GameType.SPECTATOR;

    GameType survival = GameType.SURVIVAL;
    GameType creative = GameType.CREATIVE;
    GameType adventure = GameType.ADVENTURE;
    GameType spector = GameType.SPECTATOR;
}
