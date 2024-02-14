package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.AdvJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.REPEATABLE;
import static org.mesdag.advjs.util.Data.REQUIRE_DONE;

@Mixin(PlayerAdvancementTracker.class)
public abstract class PlayerAdvancementTrackerMixin {
    @Shadow
    public abstract AdvancementProgress getProgress(Advancement advancement);

    @Shadow
    private ServerPlayerEntity owner;

    @Shadow public abstract boolean revokeCriterion(Advancement advancement, String criterionName);

    @Inject(method = "grantCriterion", at = @At("HEAD"), cancellable = true)
    private void advJS$checkParentDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        Identifier id = advancement.getId();
        if (!REQUIRE_DONE.containsKey(id)) return;
        Identifier[] requires = REQUIRE_DONE.get(id);
        if (requires.length == 0) {
            ConsoleJS.SERVER.error("AdvJS/requireDone: Invalid requires[] of '%s', which length is 0".formatted(id));
            return;
        }

        ServerAdvancementLoader loader = owner.server.getAdvancementLoader();
        for (Identifier requireId : requires) {
            Advancement required = requireId == AdvJS.PARENT ? advancement.getParent() : loader.get(requireId);
            if (required == null) {
                ConsoleJS.SERVER.warn("AdvJS/requireDone: Advancement '%s' is not exist, so '%s' will not check it".formatted(requireId, id));
                continue;
            }

            if (!getProgress(required).isDone()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }

    @Inject(method = "grantCriterion", at = @At(value = "RETURN"))
    private void advJS$repeat(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (REPEATABLE.contains(advancement.getId())) revokeCriterion(advancement, criterionName);
    }
}
