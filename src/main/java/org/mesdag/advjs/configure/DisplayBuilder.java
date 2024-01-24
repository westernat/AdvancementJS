package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.DisplayOffset;

import static org.mesdag.advjs.configure.Data.DISPLAY_OFFSET;

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
    private final ResourceLocation source;

    DisplayBuilder(ResourceLocation source) {
        this.icon = new ItemStack(Items.APPLE);
        this.title = Component.literal("title");
        this.description = Component.literal("description");
        this.background = null;
        this.frameType = FrameType.TASK;
        this.showToast = true;
        this.announceToChat = true;
        this.hidden = false;
        this.source = source;
    }

    @HideFromJS
    public DisplayBuilder(ResourceLocation source, DisplayInfo displayInfo) {
        this.icon = displayInfo.getIcon();
        this.title = displayInfo.getTitle();
        this.description = displayInfo.getDescription();
        this.background = displayInfo.getBackground();
        this.frameType = displayInfo.getFrame();
        this.showToast = displayInfo.shouldShowToast();
        this.announceToChat = displayInfo.shouldAnnounceChat();
        this.hidden = displayInfo.isHidden();
        this.source = source;
    }

    @Info("The ItemStack containing data for the advancement's icon.")
    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    @Info("The title of this advancement.")
    public void setTitle(Component title) {
        this.title = title;
    }

    @Info("The description text of this advancement.")
    public void setDescription(Component description) {
        this.description = description;
    }

    @Info("The directory for the background to use in this advancement tab (used only for the root advancement).")
    public void setBackground(@Nullable ResourceLocation path) {
        this.background = path;
    }

    @Nullable ResourceLocation getBackground() {
        return background;
    }

    @Info("Type of frame for the icon. Defaults to 'FrameType.TASK'.")
    public void setFrameType(FrameType frameType) {
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
    public void displayOffset(float x, float y) {
        DISPLAY_OFFSET.put(source, new DisplayOffset(x, y, false));
    }

    @Info(value = "Configure this advancement's display location.",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display."),
            @Param(name = "modifyChildren", value = "Determine should its children apply the same offset.")
        })
    public void displayOffset(float x, float y, boolean modifyChildren) {
        DISPLAY_OFFSET.put(source, new DisplayOffset(x, y, modifyChildren));
    }

    @HideFromJS
    public DisplayInfo build() {
        return new DisplayInfo(icon, title, description, background, frameType, showToast, announceToChat, hidden);
    }
}
