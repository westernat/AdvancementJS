package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class AdvHelper {
    public static boolean advNotDone(ServerPlayerEntity serverPlayer, Identifier id) {
        return !advDone(serverPlayer, id, null);
    }

    public static boolean advDone(ServerPlayerEntity serverPlayer, Identifier id, @Nullable String nullMsg) {
        Advancement advancement = serverPlayer.server.getAdvancementLoader().get(id);
        if (advancement == null) {
            if (nullMsg != null) ConsoleJS.SERVER.error(nullMsg);
            return false;
        }
        return serverPlayer.getAdvancementTracker().getProgress(advancement).isDone();
    }
}
