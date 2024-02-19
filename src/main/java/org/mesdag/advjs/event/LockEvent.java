package org.mesdag.advjs.event;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.util.AdvHelper;

import static org.mesdag.advjs.util.Data.*;

public class LockEvent {
    private static void checkDone(PlayerInteractEvent event, ResourceLocation id, Player player, Component component) {
        if (event.getLevel().isClientSide) {
            if (AdvHelper.clientAdvDone(id)) return;
        } else if (AdvHelper.advDone((ServerPlayer) player, id)) {
            return;
        }
        if (!component.equals(AdvJS.EMPTY_COMPONENT)) {
            player.sendSystemMessage(component);
        }
        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.FAIL);
    }

    @SubscribeEvent
    public static void onItemRightClick(PlayerInteractEvent.RightClickItem event) {
        Tuple<ResourceLocation, Component> tuple = LOCK_ITEM_USE.get(event.getItemStack().getItem());
        if (tuple == null) return;
        checkDone(event, tuple.getA(), event.getEntity(), tuple.getB());
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Tuple<ResourceLocation, Component> tuple = LOCK_BLOCK_INTERACT.get(event.getLevel().getBlockState(event.getPos()).getBlock());
        if (tuple == null) return;
        checkDone(event, tuple.getA(), event.getEntity(), tuple.getB());
    }

    @SubscribeEvent
    public static void onInteractEntity(PlayerInteractEvent.EntityInteract event) {
        Tuple<ResourceLocation, Component> tuple = LOCK_ENTITY_INTERACT.get(event.getTarget().getType());
        if (tuple == null) return;
        checkDone(event, tuple.getA(), event.getEntity(), tuple.getB());
    }
}
