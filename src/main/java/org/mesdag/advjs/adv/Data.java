package org.mesdag.advjs.adv;

import net.minecraft.ChatFormatting;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {
    private static final ResourceLocation UPGRADE = new ResourceLocation("story/upgrade_tools");
    private static final AdvGetter UPGRADE_ADV = new AdvGetter(UPGRADE);
    public static final ResourceLocation EGG = UPGRADE_ADV.addChild("locked_recipe", advBuilder -> advBuilder.display(displayBuilder -> {
        displayBuilder.setIcon(Items.BARRIER.getDefaultInstance());
        displayBuilder.setTitle(Component.translatable("advjs.cant_recipe"));
        displayBuilder.setDescription(Component.translatable("advjs.cant_recipe.desc"));
        displayBuilder.setFrameType(FrameType.CHALLENGE);
        displayBuilder.setHidden(true);
    })).criteria(criteriaBuilder -> criteriaBuilder.add("never", new ImpossibleTrigger.TriggerInstance())).getSavePath();

    public static final ArrayList<ResourceLocation> REMOVES = new ArrayList<>();
    public static final HashMap<ResourceLocation, AdvGetter> GETTER_MAP = new HashMap<>(Map.of(UPGRADE, UPGRADE_ADV));
    public static final HashMap<ResourceLocation, AdvBuilder> BUILDER_MAP = new HashMap<>();
    public static final HashMap<Item, ResourceLocation> LOCK_MAP = new HashMap<>();

    public static final Component ATTENTION = Component.translatable("advjs.attention").withStyle(ChatFormatting.RED);
    public static final Component ATTENTION_DESC = Component.translatable("advjs.attention.desc");
    static final ResourceLocation DEFAULT_BACKGROUND = new ResourceLocation("textures/gui/advancements/backgrounds/stone.png");
}
