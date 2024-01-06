package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public class DisplayBuilder {
    private ItemStack icon;
    private Component title;
    private Component description;
    @Nullable
    private ResourceLocation background;
    private FrameType frameType;
    private boolean showToast;
    private boolean announceToChat;
    private boolean hidden;

    DisplayBuilder() {
        this.icon = new ItemStack(Items.APPLE);
        this.title = new TextComponent("title");
        this.description = new TextComponent("description");
        this.background = null;
        this.frameType = FrameType.TASK;
        this.showToast = true;
        this.announceToChat = true;
        this.hidden = false;
    }

    public DisplayBuilder(ItemStack icon, Component title, Component description, @Nullable ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.background = background;
        this.frameType = frameType;
        this.showToast = showToast;
        this.announceToChat = announceToChat;
        this.hidden = hidden;
    }

    public void setIcon(ItemStackJS icon) {
        icon.setCount(1);
        this.icon = icon.getItemStack();
    }

    public void setTitle(Component title) {
        this.title = title;
    }

    public void setDescription(Component description) {
        this.description = description;
    }

    public void setBackground(@Nullable ResourceLocation path) {
        this.background = path;
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

    public DisplayInfo build() {
        return new DisplayInfo(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
