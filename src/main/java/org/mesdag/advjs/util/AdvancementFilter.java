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
    private @Nullable ResourceLocation path;
    private @Nullable String modid;
    private @Nullable String notMatchModid;
    private @Nullable ItemStack icon;
    private @Nullable FrameType frame;
    private @Nullable ResourceLocation parent;
    private boolean resolved = false;
    private boolean matchAll = false;

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
                } else if (modidStr.equals("*")) {
                    filter.matchAll = true;
                } else if (modidStr.startsWith("!")) {
                    String flip = modidStr.replace("!", "");
                    if (ModList.get().isLoaded(flip)) {
                        filter.notMatchModid = flip;
                    } else {
                        ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: mod '%s' not found".formatted(flip));
                    }
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: mod '%s' not found".formatted(modidStr));
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
        return path == null && modid == null && !matchAll && notMatchModid == null && icon == null && frame == null && parent == null;
    }

    public boolean matches(ResourceLocation pPath, ItemStack pIcon, FrameType pFrame, ResourceLocation pParent) {
        if (matchAll) return true;

        if (path != null) {
            if (path.equals(pPath)) {
                resolved = true;
                return true;
            }
            return false;
        }

        if (notMatchModid != null) {
            return !notMatchModid.equals(pPath.getNamespace());
        }

        if (modid != null && !modid.equals(pPath.getNamespace())) {
            return false;
        } else if (icon != null && pIcon != null && !ItemStack.isSameItemSameTags(icon, pIcon)) {
            return false;
        } else if (frame != null && frame != pFrame) {
            return false;
        } else return parent == null || parent.equals(pParent);
    }
}
