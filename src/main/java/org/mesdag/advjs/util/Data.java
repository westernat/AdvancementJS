package org.mesdag.advjs.util;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.getter.AdvGetter;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<Identifier> REMOVES = new ArrayList<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final Text ATTENTION = Text.translatable("advjs.attention").formatted(Formatting.RED);
    public static final Text ATTENTION_DESC = Text.translatable("advjs.attention.desc");
}
