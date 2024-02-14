package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.DisplayOffset;

import static org.mesdag.advjs.util.Data.DISPLAY_OFFSET;

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
    private final Identifier source;

    public DisplayBuilder(Identifier source) {
        this.icon = new ItemStack(Items.APPLE);
        this.title = Text.literal("title");
        this.description = Text.literal("description");
        this.background = null;
        this.frameType = AdvancementFrame.TASK;
        this.showToast = true;
        this.announceToChat = true;
        this.hidden = false;
        this.source = source;
    }

    @HideFromJS
    public DisplayBuilder(Identifier source, AdvancementDisplay displayInfo) {
        this.icon = displayInfo.getIcon();
        this.title = displayInfo.getTitle();
        this.description = displayInfo.getDescription();
        this.background = displayInfo.getBackground();
        this.frameType = displayInfo.getFrame();
        this.showToast = displayInfo.shouldShowToast();
        this.announceToChat = displayInfo.shouldAnnounceToChat();
        this.hidden = displayInfo.isHidden();
        this.source = source;
    }

    @Info("The ItemStack containing data for the advancement's icon.")
    public void setIcon(ItemStack icon) {
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

    @Info(value = "Configure this advancement's display location.",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display.")
        })
    public void offset(float x, float y) {
        DISPLAY_OFFSET.put(source, new DisplayOffset(x, y, false));
    }

    @Info(value = "Configure this advancement's display location.",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display."),
            @Param(name = "modifyChildren", value = "Determine should its children apply the same offset.")
        })
    public void offset(float x, float y, boolean modifyChildren) {
        DISPLAY_OFFSET.put(source, new DisplayOffset(x, y, modifyChildren));
    }

    @HideFromJS
    public AdvancementDisplay build() {
        return new AdvancementDisplay(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
