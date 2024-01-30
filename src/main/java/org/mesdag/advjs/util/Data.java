package org.mesdag.advjs.util;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.advancement.AdvBuilder;
import org.mesdag.advjs.advancement.AdvGetter;

import java.util.HashSet;
import java.util.Hashtable;

public class Data {
    public static final HashSet<AdvRemoveFilter> FILTERS = new HashSet<>();
    public static final Hashtable<ResourceLocation, AdvGetter> GETTER_MAP = new Hashtable<>();
    public static final Hashtable<ResourceLocation, AdvBuilder> BUILDER_MAP = new Hashtable<>();
    public static final Hashtable<ItemPredicate, ResourceLocation> LOCK_MAP = new Hashtable<>();
    public static final Hashtable<ResourceLocation, ResourceLocation[]> REQUIRE_DONE = new Hashtable<>();
    public static final Hashtable<ResourceLocation, DisplayOffset> DISPLAY_OFFSET = new Hashtable<>();

    public static void clear() {
        FILTERS.clear();
        GETTER_MAP.clear();
        BUILDER_MAP.clear();
        LOCK_MAP.clear();
        REQUIRE_DONE.clear();
        DISPLAY_OFFSET.clear();
    }
}
