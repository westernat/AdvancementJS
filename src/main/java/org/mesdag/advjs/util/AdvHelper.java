package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientAdvancements;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.mixin.client.ClientAdvancementsAccessor;

import java.util.Map;

public class AdvHelper {
    public static boolean advDone(ServerPlayer serverPlayer, @Nullable ResourceLocation id) {
        return advDone(serverPlayer, id, null);
    }

    @Info(params = {
        @Param(name = "serverPlayer"),
        @Param(name = "id"),
        @Param(name = "nullMsg", value = "The message to send if advancement failed to get.")
    })
    public static boolean advDone(ServerPlayer serverPlayer, @Nullable ResourceLocation id, @Nullable String nullMsg) {
        if (id == null) return true; // if failed to get advancement id, return true
        return advDone(serverPlayer, serverPlayer.server.getAdvancements().getAdvancement(id), nullMsg);
    }

    public static boolean advDone(ServerPlayer serverPlayer, @Nullable Advancement advancement, @Nullable String nullMsg) {
        if (advancement == null) {
            if (nullMsg != null) ConsoleJS.SERVER.error(nullMsg);
            return false;
        }
        return serverPlayer.getAdvancements().getOrStartProgress(advancement).isDone();
    }

    @HideFromJS
    @OnlyIn(Dist.CLIENT)
    public static boolean clientAdvDone(@Nullable ResourceLocation id) {
        if (id == null) return true; // if failed to get advancement id, return true
        ClientPacketListener connection = Minecraft.getInstance().getConnection();
        if (connection == null) return false;

        ClientAdvancements manager = connection.getAdvancements();
        Advancement advancement = manager.getAdvancements().get(id);
        if (advancement == null) return false;

        Map<Advancement, AdvancementProgress> progresses = ((ClientAdvancementsAccessor) manager).getProgress();
        AdvancementProgress progress = progresses.get(advancement);
        return progress != null && progress.isDone();
    }
}
