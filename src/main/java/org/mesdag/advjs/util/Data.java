package org.mesdag.advjs.util;

import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.getter.AdvGetter;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<Identifier> REMOVES = new ArrayList<>();
    public static final HashMap<Identifier, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final Text ATTENTION = new TranslatableText("advjs.attention").formatted(Formatting.RED);
    public static final Text ATTENTION_DESC = new TranslatableText("advjs.attention.desc");
}
