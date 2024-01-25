package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.REQUIRE_DONE;

@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementTrackerMixin {
    @Shadow
    public abstract AdvancementProgress getProgress(Advancement advancement);

    @Shadow
    private ServerPlayerEntity owner;

    @Inject(method = "grantCriterion", at = @At("HEAD"), cancellable = true)
    private void advJS$checkParentDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        Identifier id = advancement.getId();
        if (!REQUIRE_DONE.containsKey(id)) {
            return;
        }

        Identifier[] requires = REQUIRE_DONE.get(id);
        if (requires.length == 0) {
            Advancement parent = advancement.getParent();
            if (parent == null) {
                ConsoleJS.SERVER.warn("AdvJS/requireParentDone: Advancement '%s' is a root, so it can't check parent done".formatted(id));
                return;
            }

            if (!getProgress(parent).isDone()) {
                cir.setReturnValue(false);
            }
            return;
        }

        ServerAdvancementLoader loader = owner.server.getAdvancementLoader();
        for (Identifier requireId : requires) {
            Advancement required = loader.get(requireId);
            if (required == null) {
                ConsoleJS.SERVER.warn("AdvJS/requireParentDone: Advancement '%s' is not exist, so '%s' will not check it".formatted(requireId, id));
                continue;
            }

            if (!getProgress(required).isDone()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }
}
