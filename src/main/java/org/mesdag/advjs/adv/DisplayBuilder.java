package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class DisplayBuilder {
    private ItemStack icon;
    private Text title;
    private Text description;
    @Nullable
    private Identifier background;
    private AdvancementFrame frameType;
    private boolean showToast;
    private boolean announceToChat;
    private boolean hidden;

    DisplayBuilder() {
        this.icon = new ItemStack(Items.APPLE);
        this.title = new LiteralText("title");
        this.description = new LiteralText("description");
        this.background = null;
        this.frameType = AdvancementFrame.TASK;
        this.showToast = true;
        this.announceToChat = true;
        this.hidden = false;
    }

    public DisplayBuilder(ItemStack icon, Text title, Text description, @Nullable Identifier background, AdvancementFrame frameType, boolean showToast, boolean announceToChat, boolean hidden) {
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

    public void setTitle(Text title) {
        this.title = title;
    }

    public void setDescription(Text description) {
        this.description = description;
    }

    public void setBackground(@Nullable Identifier path) {
        this.background = path;
    }

    @Nullable Identifier getBackground() {
        return background;
    }

    public void setFrameType(AdvancementFrame frameType) {
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

    public AdvancementDisplay build() {
        return new AdvancementDisplay(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
