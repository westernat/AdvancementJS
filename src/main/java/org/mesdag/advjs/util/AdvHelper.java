package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientAdvancementManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.mixin.client.ClientAdvancementsAccessor;

import java.util.Map;

public class AdvHelper {
    public static boolean advDone(ServerPlayerEntity serverPlayer, Identifier id) {
        return advDone(serverPlayer, id, null);
    }

    @Info(params = {
        @Param(name = "serverPlayer"),
        @Param(name = "id"),
        @Param(name = "nullMsg", value = "The message to send if advancement failed to get.")
    })
    public static boolean advDone(ServerPlayerEntity serverPlayer, @Nullable Identifier id, @Nullable String nullMsg) {
        if (id == null) return true; // if failed to get advancement id, return true
        return advDone(serverPlayer, serverPlayer.server.getAdvancementLoader().get(id), nullMsg);
    }

    public static boolean advDone(ServerPlayerEntity serverPlayer, @Nullable Advancement advancement, @Nullable String nullMsg) {
        if (advancement == null) {
            if (nullMsg != null) ConsoleJS.SERVER.error(nullMsg);
            return false;
        }
        return serverPlayer.getAdvancementTracker().getProgress(advancement).isDone();
    }

    @HideFromJS
    @Environment(EnvType.CLIENT)
    public static boolean clientAdvDone(@Nullable Identifier id) {
        if (id == null) return true; // if failed to get advancement id, return true
        ClientPlayNetworkHandler connection = MinecraftClient.getInstance().getNetworkHandler();
        if (connection == null) return false;

        ClientAdvancementManager manager = connection.getAdvancementHandler();
        Advancement advancement = manager.getManager().get(id);
        if (advancement == null) return false;

        Map<Advancement, AdvancementProgress> progresses = ((ClientAdvancementsAccessor) manager).getAdvancementProgresses();
        AdvancementProgress progress = progresses.get(advancement);
        return progress != null && progress.isDone();
    }
}
