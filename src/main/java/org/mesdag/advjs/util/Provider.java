package org.mesdag.advjs.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Provider {
    private static final List<ResourceLocation> overworldBiomes = MultiNoiseBiomeSourceParameterList.Preset.OVERWORLD.usedBiomes().map(ResourceKey::location).toList();
    private static final List<ResourceLocation> netherBiomes = MultiNoiseBiomeSourceParameterList.Preset.NETHER.usedBiomes().map(ResourceKey::location).toList();
    private static final List<ResourceLocation> smithingTrims = VanillaRecipeProvider.smithingTrims().values().stream().toList();
    private static final List<ResourceLocation> BREEDABLE_ANIMALS = Stream.of(EntityType.HORSE, EntityType.DONKEY, EntityType.MULE, EntityType.SHEEP, EntityType.COW, EntityType.MOOSHROOM, EntityType.PIG, EntityType.CHICKEN, EntityType.WOLF, EntityType.OCELOT, EntityType.RABBIT, EntityType.LLAMA, EntityType.CAT, EntityType.PANDA, EntityType.FOX, EntityType.BEE, EntityType.HOGLIN, EntityType.STRIDER, EntityType.GOAT, EntityType.AXOLOTL, EntityType.CAMEL).map(BuiltInRegistries.ENTITY_TYPE::getKey).toList();
    private static final List<ResourceLocation> INDIRECTLY_BREEDABLE_ANIMALS = Stream.of(EntityType.TURTLE, EntityType.FROG, EntityType.SNIFFER).map(BuiltInRegistries.ENTITY_TYPE::getKey).toList();
    private static final List<ResourceLocation> FISH = Stream.of(Items.COD, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.SALMON).map(BuiltInRegistries.ITEM::getKey).toList();
    private static final List<ResourceLocation> FISH_BUCKETS = Stream.of(Items.COD_BUCKET, Items.TROPICAL_FISH_BUCKET, Items.PUFFERFISH_BUCKET, Items.SALMON_BUCKET).map(BuiltInRegistries.ITEM::getKey).toList();
    private static final List<ResourceLocation> EDIBLE_ITEMS = Stream.of(Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE, Items.MELON_SLICE, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.GOLDEN_CARROT, Items.PUMPKIN_PIE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.CHORUS_FRUIT, Items.BEETROOT, Items.BEETROOT_SOUP, Items.DRIED_KELP, Items.SUSPICIOUS_STEW, Items.SWEET_BERRIES, Items.HONEY_BOTTLE, Items.GLOW_BERRIES).map(BuiltInRegistries.ITEM::getKey).toList();
    private static final List<ResourceLocation> WAX_SCRAPING_TOOLS = Stream.of(Items.WOODEN_AXE, Items.GOLDEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE).map(BuiltInRegistries.ITEM::getKey).toList();
    private static final List<ResourceLocation> catVariants = Arrays.stream(EntityPredicateBuilder.CatType.values()).map(catType -> catType.variant.location()).toList();

    public List<ResourceLocation> getOverworldBiomes() {
        return overworldBiomes;
    }

    public List<ResourceLocation> getNetherBiomes() {
        return netherBiomes;
    }

    public List<ResourceLocation> getSmithingTrims() {
        return smithingTrims;
    }

    public List<ResourceLocation> getBreedableAnimals() {
        return BREEDABLE_ANIMALS;
    }

    public List<ResourceLocation> getIndirectlyBreedableAnimals() {
        return INDIRECTLY_BREEDABLE_ANIMALS;
    }

    public List<ResourceLocation> getFish() {
        return FISH;
    }

    public List<ResourceLocation> getFishBuckets() {
        return FISH_BUCKETS;
    }

    public List<ResourceLocation> getEdibleItems() {
        return EDIBLE_ITEMS;
    }

    public List<ResourceLocation> getWaxScrapingTools() {
        return WAX_SCRAPING_TOOLS;
    }

    public List<ResourceLocation> getCatVariants(){
        return catVariants;
    }
}
