package org.mesdag.advjs.getter;

import dev.latvian.mods.kubejs.core.ComponentKJS;
import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class DisplayBuilder {
    private ItemStack icon = new ItemStack(Items.APPLE);
    private Component title = new TextComponent("title");
    private Component description = new TextComponent("description");
    @Nullable
    private ResourceLocation background = null;
    private FrameType frameType = FrameType.TASK;
    private boolean showToast = true;
    private boolean announceToChat = true;
    private boolean hidden = false;


    public void setIcon(ItemStackJS icon) {
        icon.setCount(1);
        this.icon = icon.getItemStack();
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setTitle(ComponentKJS title) {
        this.title = title;
    }

    public Component getTitle() {
        return title;
    }

    public void setDescription(ComponentKJS description) {
        this.description = description;
    }

    public Component getDescription() {
        return description;
    }

    public void setBackground(String path) {
        this.background = new ResourceLocation(path);
    }

    public @Nullable ResourceLocation getBackground() {
        return background;
    }

    public void setFrameType(FrameType frameType) {
        this.frameType = frameType;
    }

    public FrameType getFrameType() {
        return frameType;
    }

    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    public boolean isShowToast() {
        return showToast;
    }

    public void setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    public boolean isAnnounceToChat() {
        return announceToChat;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}
