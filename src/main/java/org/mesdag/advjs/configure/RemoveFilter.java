package org.mesdag.advjs.configure;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.Registry;
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
                Item item = Registry.ITEM.getOptional(new ResourceLocation(icon)).orElse(Items.AIR);
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

    public boolean matches(ResourceLocation path, Item icon, String frame, String parent) {
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
