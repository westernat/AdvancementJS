package org.mesdag.advjs.integration.revelationary;

import de.dafuqs.revelationary.ClientAdvancements;
import de.dafuqs.revelationary.ClientRevelationHolder;
import de.dafuqs.revelationary.api.advancements.ClientAdvancementPacketCallback;
import dev.latvian.mods.kubejs.client.ClientEventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Set;

public class AdvRevelationEventJS extends ClientEventJS implements ClientAdvancementPacketCallback {
    public static final AdvRevelationEventJS INSTANCE = new AdvRevelationEventJS();
    private AdvancementCallBack advancementCallBack;

    public AdvRevelationEventJS() {
        ClientAdvancementPacketCallback.registerCallback(this);
    }

    @Info("Triggered when the client instance get's synched an advancement package.")
    public void onFlush(AdvancementCallBack callBack) {
        this.advancementCallBack = callBack;
    }

    public boolean hasDone(Identifier advancement) {
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
    public void onClientAdvancementPacket(Set<Identifier> doneAdvancements, Set<Identifier> removedAdvancements, boolean isFirstPacket) {
        if (advancementCallBack != null) {
            advancementCallBack.flush(doneAdvancements, removedAdvancements, isFirstPacket);
        }
    }

    @FunctionalInterface
    public interface AdvancementCallBack {
        void flush(Set<Identifier> doneAdvancements, Set<Identifier> removedAdvancements, boolean isFirstPacket);
    }
}
