package org.mesdag.advjs.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.NonNullList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.mesdag.advjs.util.Data.LOCK_MAP;

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {
    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(method = "doClick", at = @At("HEAD"), cancellable = true)
    private void advJS$checkAdvancement(int index, int p_150432_, ClickType clickType, Player player, CallbackInfo ci) {
        if (index < 0) {
            return;
        }

        Slot slot = slots.get(index);
        if (!(slot.container instanceof ResultContainer)) {
            return;
        }

        if (clickType == ClickType.PICKUP && player instanceof ServerPlayer serverPlayer) {
            Item result = slot.getItem().getItem();
            MinecraftServer server = serverPlayer.getServer();
            if (server == null || !LOCK_MAP.containsKey(result)) {
                return;
            }

            Advancement advancement = server.getAdvancements().getAdvancement(LOCK_MAP.get(result));
            if (advancement != null && !serverPlayer.getAdvancements().getOrStartProgress(advancement).isDone()) {
                ci.cancel();
            }
        }
    }
}
