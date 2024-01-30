package org.mesdag.advjs.integration.revelationary;

import de.dafuqs.revelationary.ClientAdvancements;
import de.dafuqs.revelationary.ClientRevelationHolder;
import de.dafuqs.revelationary.api.advancements.ClientAdvancementPacketCallback;
import dev.latvian.mods.kubejs.client.ClientEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class ClientAdvancementEventJS extends ClientEventJS implements ClientAdvancementPacketCallback {
    public static final ClientAdvancementEventJS INSTANCE = new ClientAdvancementEventJS();
    private AdvancementCallBack advancementCallBack;

    public ClientAdvancementEventJS() {
        ClientAdvancementPacketCallback.registerCallback(this);
    }

    @Info("Triggered when the client instance get's synched an advancement package.")
    public void onFlush(AdvancementCallBack callBack) {
        this.advancementCallBack = callBack;
    }

    public boolean hasDone(ResourceLocation advancement) {
        return ClientAdvancements.hasDone(advancement);
    }

    public boolean isBlockCloaked(Block block) {
        return ClientRevelationHolder.isCloaked(block);
    }

    public boolean isBlockStateCloaked(BlockState blockState) {
        return ClientRevelationHolder.isCloaked(blockState);
    }

    public boolean isItemCloaked(Item item) {
        return ClientRevelationHolder.isCloaked(item);
    }

    public BlockState getBlockStateCloakTarget(BlockState blockState) {
        return ClientRevelationHolder.getCloakTarget(blockState);
    }

    public Item getItemCloakTarget(Item item) {
        return ClientRevelationHolder.getCloakTarget(item);
    }

    @HideFromJS
    @Override
    public void onClientAdvancementPacket(Set<ResourceLocation> doneAdvancements, Set<ResourceLocation> removedAdvancements, boolean isFirstPacket) {
        if (advancementCallBack != null) {
            advancementCallBack.flush(doneAdvancements, removedAdvancements, isFirstPacket);
        }
    }

    @FunctionalInterface
    public interface AdvancementCallBack {
        void flush(Set<ResourceLocation> doneAdvancements, Set<ResourceLocation> removedAdvancements, boolean isFirstPacket);
    }
}
