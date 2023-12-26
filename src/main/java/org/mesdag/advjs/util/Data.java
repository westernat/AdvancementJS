package org.mesdag.advjs.util;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.getter.AdvGetter;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<ResourceLocation> removes = new ArrayList<>();
    public static final HashMap<ResourceLocation, AdvGetter> getterMap = new HashMap<>();
    public static final Component attention = new TranslatableComponent("advjs.attention").withStyle(ChatFormatting.RED);
    public static final Component attention_desc = new TranslatableComponent("advjs.attention.desc");
}
