package org.mesdag.advjs.configure;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.HashSet;

public class Data {

    public static final HashSet<RemoveFilter> FILTERS = new HashSet<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>();
    public static final HashMap<ResourceLocation, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, ResourceLocation> LOCK_MAP = new HashMap<>();
    public static final HashSet<ResourceLocation> REQUIRE_DONE = new HashSet<>();

    public static final Component ATTENTION = Component.translatable("advjs.attention").withStyle(ChatFormatting.RED);
    public static final Component ATTENTION_DESC = Component.translatable("advjs.attention.desc");
    static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("textures/gui/advancements/backgrounds/stone.png");
}
