package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class AdvHelper {
    public static boolean advNotDone(ServerPlayer serverPlayer, ResourceLocation id) {
        return !advDone(serverPlayer, id, null);
    }

    public static boolean advDone(ServerPlayer serverPlayer, ResourceLocation id, @Nullable String nullMsg) {
        Advancement advancement = serverPlayer.server.getAdvancements().getAdvancement(id);
        if (advancement == null) {
            if (nullMsg != null) ConsoleJS.SERVER.error(nullMsg);
            return false;
        }
        return serverPlayer.getAdvancements().getOrStartProgress(advancement).isDone();
    }
}
