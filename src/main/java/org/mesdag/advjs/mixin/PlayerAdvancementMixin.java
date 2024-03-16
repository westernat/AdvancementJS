package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.util.FakePlayer;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.util.AdvHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.mesdag.advjs.util.Data.*;

@Mixin(PlayerAdvancements.class)
public abstract class PlayerAdvancementMixin {
    @Shadow
    private ServerPlayer player;

    @Shadow
    public abstract boolean revoke(Advancement p_135999_, String p_136000_);

    @Inject(method = "award", at = @At("HEAD"), cancellable = true)
    private void advJS$checkDone(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (player instanceof FakePlayer) {
            cir.setReturnValue(false);
            return;
        }
        ResourceLocation id = advancement.getId();

        ResourceLocation[] requires = REQUIRE_DONE.get(id);
        if (requires != null) {
            if (requires.length == 0) {
                ConsoleJS.SERVER.error("AdvJS/requireDone: Invalid requires[] of '%s', which length is 0".formatted(id));
                return;
            }
            ServerAdvancementManager manager = player.server.getAdvancements();
            for (ResourceLocation requireId : requires) {
                Advancement required = requireId == AdvJS.PARENT ? advancement.getParent() : manager.getAdvancement(requireId);
                String nullMsg = "AdvJS/requireDone: Advancement '%s' is not exist, so '%s' will not check it".formatted(requireId, id);
                if (!AdvHelper.advDone(player, required, nullMsg)) {
                    cir.setReturnValue(false);
                    return;
                }
            }
        }

        ResourceLocation[] any = ANY_DONE.get(id);
        if (any == null) return;
        if (any.length == 0) {
            ConsoleJS.SERVER.error("AdvJS/anyDone: Invalid requires[] of '%s', which length is 0".formatted(id));
            return;
        }
        ServerAdvancementManager manager = player.server.getAdvancements();
        for (ResourceLocation requireId : any) {
            Advancement required = requireId == AdvJS.PARENT ? advancement.getParent() : manager.getAdvancement(requireId);
            String nullMsg = "AdvJS/anyDone: Advancement '%s' is not exist, so '%s' will not check it".formatted(requireId, id);
            if (AdvHelper.advDone(player, required, nullMsg)) {
                cir.setReturnValue(true);
                return;
            }
        }
    }

    @Inject(method = "award", at = @At(value = "RETURN", ordinal = 1))
    private void advJS$repeat(Advancement advancement, String criterionName, CallbackInfoReturnable<Boolean> cir) {
        if (REPEATABLE.contains(advancement.getId())) revoke(advancement, criterionName);
    }
}
