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
import org.mesdag.advjs.AdvJS;
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
        if (slotIndex < 0 || !(player instanceof ServerPlayerEntity serverPlayer)) return;

//      ResourceLocation slot_lock_by = LOCK_SLOT.get(index);
//      if (LOCK_SLOT.containsKey(index) && AdvHelper.advNotDone(serverPlayer, slot_lock_by)) {
//          ci.cancel();
//          AdvJS.debugInfo("AdvJS/lockSlot: slot index '%s' has locked by '%s'".formatted(index, slot_lock_by));
//          return;
//      }

        Slot slot = slots.get(slotIndex);
        if (!(slot.inventory instanceof CraftingResultInventory)) return;

        ItemStack result = slot.getStack();
        for (Map.Entry<ItemPredicate, Identifier> entry : LOCK_RESULT.entrySet()) {
            Identifier result_lock_by = entry.getValue();
            if (entry.getKey().test(result) && !AdvHelper.advDone(serverPlayer, result_lock_by)) {
                ci.cancel();
                AdvJS.debugInfo("AdvJS/lockResult: result '%s' has locked by '%s'".formatted(result.getItem().kjs$getId(), result_lock_by));
                return;
            }
        }
    }
}
