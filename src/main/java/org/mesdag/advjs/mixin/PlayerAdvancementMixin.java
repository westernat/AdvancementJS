package org.mesdag.advjs.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.configure.Data.REQUIRE_DONE;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementMixin {
    @Shadow
    private ServerPlayer player;

    @Shadow
    public abstract AdvancementProgress getOrStartProgress(Advancement p_135997_);

    @Inject(method = "award", at = @At("HEAD"), cancellable = true)
    private void advJS$checkParentDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (player instanceof FakePlayer) {
            cir.setReturnValue(false);
            return;
        }

        if (REQUIRE_DONE.contains(advancement.getId())) {
            Advancement parent = advancement.getParent();
            if (parent != null && !getOrStartProgress(parent).isDone()) {
                cir.setReturnValue(false);
            }
        }
    }
}
