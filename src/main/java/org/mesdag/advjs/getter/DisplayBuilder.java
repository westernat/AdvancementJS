package org.mesdag.advjs.getter;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DisplayBuilder {
    private ItemStack icon = new ItemStack(Items.APPLE);
    private Text title = new LiteralText("title");
    private Text description = new LiteralText("description");
    @Nullable
    private Identifier background = null;
    private AdvancementFrame frameType = AdvancementFrame.TASK;
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

    public void setTitle(Text title) {
        this.title = title;
    }

    public Text getTitle() {
        return title;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public Text getDescription() {
        return description;
    }

    public void setBackground(String path) {
        this.background = new Identifier(path);
    }

    public @Nullable Identifier getBackground() {
        return background;
    }

    public void setFrameType(AdvancementFrame frameType) {
        this.frameType = frameType;
    }

    public AdvancementFrame getFrameType() {
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
