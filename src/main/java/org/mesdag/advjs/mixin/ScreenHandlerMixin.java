package org.mesdag.advjs.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static org.mesdag.advjs.util.Data.LOCK_MAP;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {
    @Shadow
    @Final
    public DefaultedList<Slot> slots;

    @Inject(method = "internalOnSlotClick", at = @At("HEAD"), cancellable = true)
    private void advJS$checkAdvancement(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (slotIndex < 0) {
            return;
        }

        Slot slot = slots.get(slotIndex);
        if (!(slot.inventory instanceof CraftingResultInventory)) {
            return;
        }

        if (actionType == SlotActionType.PICKUP && player instanceof ServerPlayerEntity serverPlayer) {
            Item result = slot.getStack().getItem();
            MinecraftServer server = serverPlayer.getServer();
            if (server == null || !LOCK_MAP.containsKey(result)) {
                return;
            }

            Advancement advancement = server.getAdvancementLoader().get(LOCK_MAP.get(result));
            if (advancement != null && !serverPlayer.getAdvancementTracker().getProgress(advancement).isDone()) {
                ci.cancel();
            }
        }
    }
}
