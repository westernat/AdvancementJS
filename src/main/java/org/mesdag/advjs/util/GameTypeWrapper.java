package org.mesdag.advjs.util;

import net.minecraft.world.GameMode;

public interface GameTypeWrapper {
    GameMode SURVIVAL = GameMode.SURVIVAL;
    GameMode CREATIVE = GameMode.CREATIVE;
    GameMode ADVENTURE = GameMode.ADVENTURE;
    GameMode SPECTATOR = GameMode.SPECTATOR;

    GameMode survival = GameMode.SURVIVAL;
    GameMode creative = GameMode.CREATIVE;
    GameMode adventure = GameMode.ADVENTURE;
    GameMode spector = GameMode.SPECTATOR;
}
