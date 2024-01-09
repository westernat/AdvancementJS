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

    public boolean matches(Identifier path, Item icon, String frame, String parent) {
        if (this.path != null && !this.path.equals(path)) {
            return false;
        } else {
            boolean flag = this.modid != null || this.icon != null || this.frame != null || this.parent != null;
            if (flag && this.modid != null) {
                flag = this.modid.equals(path.getNamespace());
            }
            if (flag && this.icon != null) {
                flag = this.icon == icon;
            }
            if (flag && this.frame != null) {
                flag = this.frame.equals(frame);
            }
            if (flag && this.parent != null) {
                flag = this.parent.equals(parent);
            }
            return flag;
        }
    }
}
