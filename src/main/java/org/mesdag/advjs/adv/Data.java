package org.mesdag.advjs.adv;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<Identifier> REMOVES = new ArrayList<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<Identifier, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final Text ATTENTION = Text.translatable("advjs.attention").formatted(Formatting.RED);
    public static final Text ATTENTION_DESC = Text.translatable("advjs.attention.desc");
    static final Identifier DEFAULT_BACKGROUND = new Identifier("textures/gui/advancements/backgrounds/stone.png");
}
