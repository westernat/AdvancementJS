package org.mesdag.advjs.adv;

import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class DisplayBuilder {
    private ItemStack icon = new ItemStack(Items.APPLE);
    private Component title = Component.literal("title");
    private Component description = Component.literal("description");
    @Nullable
    private ResourceLocation background = null;
    private FrameType frameType = FrameType.TASK;
    private boolean showToast = true;
    private boolean announceToChat = true;
    private boolean hidden = false;


    public void setIcon(ItemStack icon) {
        icon.setCount(1);
        this.icon = icon;
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public void setDescription(Component description) {
        this.description = description;
    }

    public void setBackground(String path) {
        this.background = new ResourceLocation(path);
    }

    @Nullable ResourceLocation getBackground() {
        return background;
    }

    public void setFrameType(FrameType frameType) {
        this.frameType = frameType;
    }

    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    public void setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    DisplayInfo build() {
        return new DisplayInfo(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
