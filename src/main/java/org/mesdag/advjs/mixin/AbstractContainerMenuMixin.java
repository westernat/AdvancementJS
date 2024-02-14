package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.core.ItemKJS;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
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

@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {
    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(method = "doClick", at = @At("HEAD"), cancellable = true)
    private void advJS$checkAdvancement(int index, int p_150432_, ClickType clickType, Player player, CallbackInfo ci) {
        if (index < 0 || !(player instanceof ServerPlayer serverPlayer)) return;

//      ResourceLocation slot_lock_by = LOCK_SLOT.get(index);
//      if (LOCK_SLOT.containsKey(index) && AdvHelper.advNotDone(serverPlayer, slot_lock_by)) {
//          ci.cancel();
//          AdvJS.debugInfo("AdvJS/lockSlot: slot index '%s' has locked by '%s'".formatted(index, slot_lock_by));
//          return;
//      }

        Slot slot = slots.get(index);
        if (!(slot.container instanceof ResultContainer) || clickType != ClickType.PICKUP) return;

        ItemStack result = slot.getItem();
        for (Map.Entry<ItemPredicate, ResourceLocation> entry : LOCK_RESULT.entrySet()) {
            ResourceLocation result_lock_by = entry.getValue();
            if (entry.getKey().matches(result) && !AdvHelper.advDone(serverPlayer, result_lock_by)) {
                ci.cancel();
                AdvJS.debugInfo("AdvJS/lockResult: result '%s' has locked by '%s'".formatted(((ItemKJS) result.getItem()).kjs$getId(), result_lock_by));
                return;
            }
        }
    }
}
