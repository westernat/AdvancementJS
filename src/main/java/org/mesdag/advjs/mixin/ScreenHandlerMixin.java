package org.mesdag.advjs.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

import static org.mesdag.advjs.util.Data.LOCK_MAP;

@Mixin(ScreenHandler.class)
public abstract class ScreenHandlerMixin {
    @Shadow
    @Final
    public DefaultedList<Slot> slots;

    @Inject(method = "internalOnSlotClick", at = @At("HEAD"), cancellable = true)
    private void advJS$checkAdvancement(int slotIndex, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo ci) {
        if (slotIndex < 0) return;
        Slot slot = slots.get(slotIndex);
        if (!(slot.inventory instanceof CraftingResultInventory)) return;

        if (actionType == SlotActionType.PICKUP && player instanceof ServerPlayerEntity serverPlayer) {
            ItemStack result = slot.getStack();
            MinecraftServer server = serverPlayer.getServer();
            if (server == null) return;

            HashSet<Identifier> lockBy = new HashSet<>();
            LOCK_MAP.forEach((key, value) -> {
                if (key.test(result)) {
                    lockBy.add(value);
                }
            });

            for (Identifier id : lockBy) {
                Advancement advancement = server.getAdvancementLoader().get(id);
                if (advancement != null && !serverPlayer.getAdvancementTracker().getProgress(advancement).isDone()) {
                    ci.cancel();
                }
            }
        }
    }
}
