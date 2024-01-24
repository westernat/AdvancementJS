package org.mesdag.advjs.configure;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.DisplayOffset;

import java.util.HashMap;
import java.util.HashSet;

public class Data {
    public static final HashSet<RemoveFilter> FILTERS = new HashSet<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<Identifier, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, Identifier> LOCK_MAP = new HashMap<>();
    public static final HashSet<Identifier> REQUIRE_DONE = new HashSet<>();
    public static final HashMap<Identifier, DisplayOffset> DISPLAY_OFFSET = new HashMap<>();
}
