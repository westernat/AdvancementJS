package org.mesdag.advjs.mixin.client;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.multiplayer.ClientAdvancements;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(ClientAdvancements.class)
public interface ClientAdvancementsAccessor {
    @Accessor
    Map<Advancement, AdvancementProgress> getProgress();
}
