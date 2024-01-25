package org.mesdag.advjs.util;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.kubejs.util.MapJS;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;


public class AdvRemoveFilter {
    @Nullable Identifier path;
    @Nullable String modid;
    @Nullable Item icon;
    @Nullable String frame;
    @Nullable String parent;
    private boolean resolved = false;

    public static AdvRemoveFilter of(Object o) {
        AdvRemoveFilter filter = new AdvRemoveFilter();
        if (o instanceof CharSequence charSequence) {
            filter.path = new Identifier(charSequence.toString());
        } else if (o instanceof Map) {
            JsonObject jsonObject = MapJS.json(o);
            if (jsonObject == null) {
                return filter;
            }

            if (jsonObject.has("mod")) {
                String modid = jsonObject.get("mod").getAsString();
                if (FabricLoader.getInstance().isModLoaded(modid)) {
                    filter.modid = modid;
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: Mod '%s' not found".formatted(modid));
                }
            }

            if (jsonObject.has("icon")) {
                Identifier id = new Identifier(jsonObject.get("icon").getAsString());
                Item item = Registries.ITEM.containsId(id) ? Registries.ITEM.get(id) : Items.AIR;
                if (item != Items.AIR) {
                    filter.icon = item;
                } else {
                    ConsoleJS.SERVER.warn("AdvJS/RemoveFilter: Icon '%s' not found".formatted(id));
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

    public boolean matches(Identifier path, Item icon, String frame, String parent) {
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
