package org.mesdag.advjs.mixin;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.configure.Data.REQUIRE_DONE;

@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementTrackerMixin {
    @Shadow
    public abstract AdvancementProgress getProgress(Advancement advancement);

    @Inject(method = "grantCriterion", at = @At("HEAD"), cancellable = true)
    private void advJS$checkParentDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (REQUIRE_DONE.contains(advancement.getId())) {
            Advancement parent = advancement.getParent();
            if (parent != null && !getProgress(parent).isDone()) {
                cir.setReturnValue(false);
            }
        }
    }
}
