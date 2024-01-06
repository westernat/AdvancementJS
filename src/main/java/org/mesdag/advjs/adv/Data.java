package org.mesdag.advjs.adv;

import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<Identifier> REMOVES = new ArrayList<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<Identifier, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, Identifier> LOCK_MAP = new HashMap<>();
    public static final Text ATTENTION = new TranslatableText("advjs.attention").formatted(Formatting.RED);
    public static final Text ATTENTION_DESC = new TranslatableText("advjs.attention.desc");
    static final Identifier DEFAULT_BACKGROUND = new Identifier("textures/gui/advancements/backgrounds/stone.png");
}
