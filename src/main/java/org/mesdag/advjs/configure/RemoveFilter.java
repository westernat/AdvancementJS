package org.mesdag.advjs.configure;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.AdvJS;


public class RemoveFilter {
    @Nullable Identifier path;
    @Nullable String modid;
    @Nullable Item icon;
    @Nullable String frame;
    @Nullable String parent;
    private boolean resolved = false;

    public static RemoveFilter of(JsonElement jsonElement) {
        RemoveFilter filter = new RemoveFilter();
        if (jsonElement.isJsonObject()) {
            JsonObject object = jsonElement.getAsJsonObject();
            if (object.has("mod")) {
                // TODO if check mod loaded
                filter.modid = object.get("mod").getAsString();
            }

            if (object.has("icon")) {
                String icon = object.get("icon").getAsString();
                Identifier id = new Identifier(icon);
                Item item = Registries.ITEM.containsId(id) ? Registries.ITEM.get(id) : Items.AIR;
                if (item != Items.AIR) {
                    filter.icon = item;
                } else {
                    AdvJS.LOGGER.warn("RemoveFilter: Icon '{}' not found", icon);
                }
            }

            if (object.has("frame")) {
                filter.frame = object.get("frame").getAsString();
            }

            if (object.has("parent")) {
                filter.parent = object.get("parent").getAsString();
            }
        } else if (jsonElement.isJsonPrimitive()) {
            filter.path = new Identifier(jsonElement.getAsString());
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
