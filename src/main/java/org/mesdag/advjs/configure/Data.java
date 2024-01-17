package org.mesdag.advjs.configure;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.HashSet;

public class Data {
    public static final HashSet<RemoveFilter> FILTERS = new HashSet<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<ResourceLocation, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, ResourceLocation> LOCK_MAP = new HashMap<>();
    public static final HashSet<ResourceLocation> REQUIRE_DONE = new HashSet<>();
}
