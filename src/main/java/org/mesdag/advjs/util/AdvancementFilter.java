package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.item.ItemStackJS;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.UtilsJS;
import net.minecraft.advancements.FrameType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class AdvancementFilter {
    @Nullable ResourceLocation path;
    @Nullable String modid;
    @Nullable ItemStack icon;
    @Nullable FrameType frame;
    @Nullable ResourceLocation parent;
    private boolean resolved = false;

    public static AdvancementFilter of(Object o) {
        AdvancementFilter filter = new AdvancementFilter();
        if (o instanceof CharSequence charSequence) {
            filter.path = new ResourceLocation(charSequence.toString());
        } else if (o instanceof Map<?, ?> map) {
            Object mod = map.get("mod");
            if (mod instanceof CharSequence modid) {
                String modidStr = modid.toString();
                if (ModList.get().isLoaded(modidStr)) {
                    filter.modid = modidStr;
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: mod '%s' not found".formatted(modid));
                }
            }

            Object icon = map.get("icon");
            if (icon != null) {
                ItemStack iconStack = ItemStackJS.of(icon);
                if (iconStack == ItemStack.EMPTY) {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: icon '%s' not found".formatted(icon));
                } else {
                    filter.icon = iconStack;
                }
            }

            Object frame = map.get("frame");
            if (frame instanceof CharSequence frameStr) {
                filter.frame = FrameType.byName(frameStr.toString());
            } else if (frame instanceof FrameType frameType) {
                filter.frame = frameType;
            }

            Object parent = map.get("parent");
            ResourceLocation parentId = UtilsJS.getMCID(null, parent);
            if (parentId != null) {
                filter.parent = parentId;
            }
        }
        return filter;
    }

    public boolean isResolved() {
        return resolved;
    }

    public boolean fail() {
        return path == null && modid == null && icon == null && frame == null && parent == null;
    }

    public boolean matches(ResourceLocation path, ItemStack icon, FrameType frame, ResourceLocation parent) {
        if (this.path != null) {
            if (this.path.equals(path)) {
                resolved = true;
                return true;
            }
            return false;
        }

        if (this.modid != null && !this.modid.equals(path.getNamespace())) {
            return false;
        } else if (this.icon != null && icon != null && !ItemStack.isSameItemSameTags(this.icon, icon)) {
            return false;
        } else if (this.frame != null && this.frame != frame) {
            return false;
        } else return this.parent == null || this.parent.equals(parent);
    }
}
