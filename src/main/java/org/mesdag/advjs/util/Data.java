package org.mesdag.advjs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.mesdag.advjs.configure.AdvBuilder;
import org.mesdag.advjs.configure.AdvGetter;

import java.util.HashMap;
import java.util.HashSet;

public class Data {
    public static final HashSet<AdvRemoveFilter> FILTERS = new HashSet<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<ResourceLocation, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, ResourceLocation> LOCK_MAP = new HashMap<>();
    public static final HashMap<ResourceLocation, ResourceLocation[]> REQUIRE_DONE = new HashMap<>();
    public static final HashMap<ResourceLocation, DisplayOffset> DISPLAY_OFFSET = new HashMap<>();
}
