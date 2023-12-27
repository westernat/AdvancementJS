package org.mesdag.advjs.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.getter.AdvGetter;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<ResourceLocation> REMOVES = new ArrayList<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final Component ATTENTION = new TranslatableComponent("advjs.attention").withStyle(ChatFormatting.RED);
    public static final Component ATTENTION_DESC = new TranslatableComponent("advjs.attention.desc");
}
