package org.mesdag.advjs.configure;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.AdvJS;


public class RemoveFilter {
    @Nullable ResourceLocation path;
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
                String modid = object.get("mod").getAsString();
                if (ModList.get().isLoaded(modid)) {
                    filter.modid = modid;
                } else {
                    AdvJS.LOGGER.warn("Mod '{}' not found", modid);
                }
            }

            if (object.has("icon")) {
                String icon = object.get("icon").getAsString();
                Item item = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(icon)).orElse(Items.AIR);
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
            filter.path = new ResourceLocation(jsonElement.getAsString());
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
