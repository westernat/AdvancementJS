package org.mesdag.advjs.util;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class AdvancementFilter {
    @Nullable ResourceLocation path;
    @Nullable String modid;
    @Nullable Item icon;
    @Nullable String frame;
    @Nullable String parent;
    private boolean resolved = false;

    public static AdvancementFilter of(Object o) {
        AdvancementFilter filter = new AdvancementFilter();
        if (o instanceof CharSequence charSequence) {
            filter.path = new ResourceLocation(charSequence.toString());
        } else if (o instanceof Map) {
            JsonObject jsonObject = MapJS.json(o);
            if (jsonObject == null) {
                return filter;
            }

            if (jsonObject.has("mod")) {
                String modid = jsonObject.get("mod").getAsString();
                if (ModList.get().isLoaded(modid)) {
                    filter.modid = modid;
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: Mod '%s' not found".formatted(modid));
                }
            }

            if (jsonObject.has("icon")) {
                String icon = jsonObject.get("icon").getAsString();
                Item item = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(icon)).orElse(Items.AIR);
                if (item != Items.AIR) {
                    filter.icon = item;
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: Icon '%s' not found".formatted(icon));
                }
            }

            if (jsonObject.has("frame")) {
                filter.frame = jsonObject.get("frame").getAsString();
            }

            if (jsonObject.has("parent")) {
                filter.parent = jsonObject.get("parent").getAsString();
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

    public boolean matches(ResourceLocation path, Item icon, String frame, String parent) {
        if (this.path != null) {
            if (this.path.equals(path)) {
                resolved = true;
                return true;
            }
            return false;
        }

        if (this.modid != null && !this.modid.equals(path.getNamespace())) {
            return false;
        } else if (this.icon != null && this.icon != icon) {
            return false;
        } else if (this.frame != null && !this.frame.equals(frame)) {
            return false;
        } else return this.parent == null || this.parent.equals(parent);
    }
}
