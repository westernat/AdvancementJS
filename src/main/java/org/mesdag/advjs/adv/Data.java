package org.mesdag.advjs.adv;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;

public class Data {
    public static final ArrayList<ResourceLocation> REMOVES = new ArrayList<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<ResourceLocation, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final Component ATTENTION = Component.translatable("advjs.attention").withStyle(ChatFormatting.RED);
    public static final Component ATTENTION_DESC = Component.translatable("advjs.attention.desc");
    static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("textures/gui/advancements/backgrounds/stone.png");
}
