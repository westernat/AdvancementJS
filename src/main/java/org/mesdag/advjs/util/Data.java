package org.mesdag.advjs.util;

import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.advancement.AdvBuilder;
import org.mesdag.advjs.advancement.AdvGetter;

import java.util.HashSet;
import java.util.Hashtable;

public class Data {
    public static final HashSet<AdvRemoveFilter> FILTERS = new HashSet<>();
    public static final Hashtable<Identifier, AdvGetter> GETTER_MAP = new Hashtable<>();
    public static final Hashtable<Identifier, AdvBuilder> BUILDER_MAP = new Hashtable<>();
    public static final Hashtable<ItemPredicate, Identifier> LOCK_MAP = new Hashtable<>();
    public static final Hashtable<Identifier, Identifier[]> REQUIRE_DONE = new Hashtable<>();
    public static final Hashtable<Identifier, DisplayOffset> DISPLAY_OFFSET = new Hashtable<>();

    public static void clear() {
        FILTERS.clear();
        GETTER_MAP.clear();
        BUILDER_MAP.clear();
        LOCK_MAP.clear();
        REQUIRE_DONE.clear();
        DISPLAY_OFFSET.clear();
    }
}
