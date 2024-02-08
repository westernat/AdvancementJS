package org.mesdag.advjs.util;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.advancement.AdvBuilder;
import org.mesdag.advjs.advancement.AdvGetter;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Data {
    public static final HashSet<AdvancementFilter> FILTERS = new HashSet<>();
    public static final HashSet<ResourceLocation> REPEATABLE = new HashSet<>();
    public static final LinkedHashMap<ResourceLocation, AdvGetter> GETTERS = new LinkedHashMap<>();
    public static final LinkedHashMap<ResourceLocation, AdvBuilder> BUILDERS = new LinkedHashMap<>();
    public static final Hashtable<ItemPredicate, ResourceLocation> LOCK_RESULT = new Hashtable<>();
    public static final Hashtable<Block, ResourceLocation> LOCK_INTERACT = new Hashtable<>();
    public static final Hashtable<ResourceLocation, ResourceLocation[]> REQUIRE_DONE = new Hashtable<>();
    public static final Hashtable<ResourceLocation, DisplayOffset> DISPLAY_OFFSET = new Hashtable<>();

    public static void clear() {
        FILTERS.clear();
        REPEATABLE.clear();
        GETTERS.clear();
        BUILDERS.clear();
        LOCK_RESULT.clear();
        LOCK_INTERACT.clear();
        REQUIRE_DONE.clear();
        DISPLAY_OFFSET.clear();
    }
}
