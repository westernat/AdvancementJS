package org.mesdag.advjs.configure;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Data {
    public static final ArrayList<Identifier> REMOVES = new ArrayList<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<Identifier, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, Identifier> LOCK_MAP = new HashMap<>();
    public static final HashSet<Identifier> REQUIRE_DONE = new HashSet<>();

    public static final Text ATTENTION = Text.translatable("advjs.attention").formatted(Formatting.RED);
    public static final Text ATTENTION_DESC = Text.translatable("advjs.attention.desc");
    static final Identifier DEFAULT_BACKGROUND = new Identifier("textures/gui/advancements/backgrounds/stone.png");
}
