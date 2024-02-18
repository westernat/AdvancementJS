package org.mesdag.advjs.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.advancement.AdvBuilder;
import org.mesdag.advjs.advancement.AdvGetter;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashMap;

public class Data {
    public static final HashSet<Identifier> REPEATABLE = new HashSet<>();
    public static final Hashtable<ItemPredicate, Identifier> LOCK_RESULT = new Hashtable<>();
    public static final Hashtable<Item, Identifier> LOCK_ITEM_USE = new Hashtable<>();
    public static final Hashtable<Block, Identifier> LOCK_BLOCK_INTERACT = new Hashtable<>();
    public static final Hashtable<EntityType<?>, Identifier> LOCK_ENTITY_INTERACT = new Hashtable<>();
    public static final Hashtable<Identifier, Identifier[]> REQUIRE_DONE = new Hashtable<>();

    public static final HashSet<AdvancementFilter> FILTERS = new HashSet<>();
    public static final LinkedHashMap<Identifier, AdvGetter> GETTERS = new LinkedHashMap<>();
    public static final LinkedHashMap<Identifier, AdvBuilder> BUILDERS = new LinkedHashMap<>();
    public static final Hashtable<Identifier, DisplayOffset> DISPLAY_OFFSET = new Hashtable<>();

    public static void clearResistance() {
        REPEATABLE.clear();
        LOCK_RESULT.clear();
        LOCK_ITEM_USE.clear();
        LOCK_BLOCK_INTERACT.clear();
        LOCK_ENTITY_INTERACT.clear();
        REQUIRE_DONE.clear();
    }

    public static void clearCache() {
        FILTERS.clear();
        GETTERS.clear();
        BUILDERS.clear();
        DISPLAY_OFFSET.clear();
    }
}
