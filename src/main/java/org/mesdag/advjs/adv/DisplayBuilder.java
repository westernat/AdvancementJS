package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        this.title = Text.literal("title");
        this.description = Text.literal("description");
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

    @Info("The ItemStack containing data for the advancement's icon.")
    public void setIcon(ItemStack icon) {
        icon.setCount(1);
        this.icon = icon;
    }

    @Info("The title of this advancement.")
    public void setTitle(Text title) {
        this.title = title;
    }

    @Info("The description text of this advancement.")
    public void setDescription(Text description) {
        this.description = description;
    }

    @Info("The directory for the background to use in this advancement tab (used only for the root advancement).")
    public void setBackground(@Nullable Identifier path) {
        this.background = path;
    }

    @Nullable Identifier getBackground() {
        return background;
    }

    @Info("Type of frame for the icon. Defaults to 'FrameType.TASK'.")
    public void setFrameType(AdvancementFrame frameType) {
        this.frameType = frameType;
    }

    @Info("Whether to show a toast to the player when this advancement has been completed. Defaults to true.")
    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }

    @Info("Whether to announce in the chat when this advancement has been completed. Defaults to true.")
    public void setAnnounceToChat(boolean announceToChat) {
        this.announceToChat = announceToChat;
    }

    @Info("""
        Whether to hide this advancement and all its children from the advancement screen until this advancement have been completed.
                
        Has no effect on root advancements themselves, but still affects all their children. Defaults to false.
        """)
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public AdvancementDisplay build() {
        return new AdvancementDisplay(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
