package org.mesdag.advjs.mixin;

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
        if (index < 0) return;
        Slot slot = slots.get(index);
        if (!(slot.container instanceof ResultContainer)) return;

        if (clickType == ClickType.PICKUP && player instanceof ServerPlayer serverPlayer) {
            ItemStack result = slot.getItem();
            for (Map.Entry<ItemPredicate, ResourceLocation> entry : LOCK_RESULT.entrySet()) {
                if (entry.getKey().matches(result) && AdvHelper.advNotDone(serverPlayer, entry.getValue())) {
                    ci.cancel();
                    return;
                }
            }
        }
    }
}
