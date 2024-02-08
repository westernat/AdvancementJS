package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import org.mesdag.advjs.AdvJS;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.REPEATABLE;
import static org.mesdag.advjs.util.Data.REQUIRE_DONE;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementMixin {
    @Shadow
    private ServerPlayer player;

    @Shadow
    public abstract AdvancementProgress getOrStartProgress(Advancement p_135997_);

    @Shadow
    public abstract boolean revoke(Advancement p_135999_, String p_136000_);

    @Inject(method = "award", at = @At("HEAD"), cancellable = true)
    private void advJS$checkDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (player instanceof FakePlayer) {
            cir.setReturnValue(false);
            return;
        }
        ResourceLocation id = advancement.getId();
        if (!REQUIRE_DONE.containsKey(id)) return;
        ResourceLocation[] requires = REQUIRE_DONE.get(id);
        if (requires.length == 0) {
            ConsoleJS.SERVER.error("AdvJS/requireDone: Invalid requires[] of '%s', which length is 0".formatted(id));
            return;
        }

        ServerAdvancementManager manager = player.server.getAdvancements();
        for (ResourceLocation requireId : requires) {
            Advancement required = requireId == AdvJS.PARENT ? advancement.getParent() : manager.getAdvancement(requireId);
            if (required == null) {
                ConsoleJS.SERVER.warn("AdvJS/requireDone: Advancement '%s' is not exist, so '%s' will not check it".formatted(requireId, id));
                continue;
            }

            if (!getOrStartProgress(required).isDone()) {
                cir.setReturnValue(false);
                return;
            }
        }
    }

    @Inject(method = "award", at = @At(value = "RETURN", ordinal = 1))
    private void advJS$repeat(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (REPEATABLE.contains(advancement.getId())) revoke(advancement, criterionName);
    }
}
