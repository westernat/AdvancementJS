package org.mesdag.advjs.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static org.mesdag.advjs.util.Data.LOCK_RESULT;

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
            for (Map.Entry<ItemPredicate, Identifier> entry : LOCK_RESULT.entrySet()){
                if(entry.getKey().test(result) && AdvHelper.advNotDone(serverPlayer, entry.getValue())){
                    ci.cancel();
                    return;
                }
            }
        }
    }
}
