package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Provider {
    private static final List<Identifier> overworldBiomes = MultiNoiseBiomeSourceParameterList.Preset.OVERWORLD.biomeStream().map(RegistryKey::getRegistry).toList();
    private static final List<Identifier> netherBiomes = MultiNoiseBiomeSourceParameterList.Preset.NETHER.biomeStream().map(RegistryKey::getRegistry).toList();
    private static final List<Identifier> smithingTrims = VanillaRecipeProvider.getTrimSmithingTemplateMap().values().stream().toList();
    private static final List<Identifier> BREEDABLE_ANIMALS = Stream.of(EntityType.HORSE, EntityType.DONKEY, EntityType.MULE, EntityType.SHEEP, EntityType.COW, EntityType.MOOSHROOM, EntityType.PIG, EntityType.CHICKEN, EntityType.WOLF, EntityType.OCELOT, EntityType.RABBIT, EntityType.LLAMA, EntityType.CAT, EntityType.PANDA, EntityType.FOX, EntityType.BEE, EntityType.HOGLIN, EntityType.STRIDER, EntityType.GOAT, EntityType.AXOLOTL, EntityType.CAMEL).map(Registries.ENTITY_TYPE::getId).toList();
    private static final List<Identifier> INDIRECTLY_BREEDABLE_ANIMALS = Stream.of(EntityType.TURTLE, EntityType.FROG, EntityType.SNIFFER).map(Registries.ENTITY_TYPE::getId).toList();
    private static final List<Identifier> FISH = Stream.of(Items.COD, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.SALMON).map(Registries.ITEM::getId).toList();
    private static final List<Identifier> FISH_BUCKETS = Stream.of(Items.COD_BUCKET, Items.TROPICAL_FISH_BUCKET, Items.PUFFERFISH_BUCKET, Items.SALMON_BUCKET).map(Registries.ITEM::getId).toList();
    private static final List<Identifier> EDIBLE_ITEMS = Stream.of(Items.APPLE, Items.MUSHROOM_STEW, Items.BREAD, Items.PORKCHOP, Items.COOKED_PORKCHOP, Items.GOLDEN_APPLE, Items.ENCHANTED_GOLDEN_APPLE, Items.COD, Items.SALMON, Items.TROPICAL_FISH, Items.PUFFERFISH, Items.COOKED_COD, Items.COOKED_SALMON, Items.COOKIE, Items.MELON_SLICE, Items.BEEF, Items.COOKED_BEEF, Items.CHICKEN, Items.COOKED_CHICKEN, Items.ROTTEN_FLESH, Items.SPIDER_EYE, Items.CARROT, Items.POTATO, Items.BAKED_POTATO, Items.POISONOUS_POTATO, Items.GOLDEN_CARROT, Items.PUMPKIN_PIE, Items.RABBIT, Items.COOKED_RABBIT, Items.RABBIT_STEW, Items.MUTTON, Items.COOKED_MUTTON, Items.CHORUS_FRUIT, Items.BEETROOT, Items.BEETROOT_SOUP, Items.DRIED_KELP, Items.SUSPICIOUS_STEW, Items.SWEET_BERRIES, Items.HONEY_BOTTLE, Items.GLOW_BERRIES).map(Registries.ITEM::getId).toList();
    private static final List<Identifier> WAX_SCRAPING_TOOLS = Stream.of(Items.WOODEN_AXE, Items.GOLDEN_AXE, Items.STONE_AXE, Items.IRON_AXE, Items.DIAMOND_AXE, Items.NETHERITE_AXE).map(Registries.ITEM::getId).toList();
    private static final List<Identifier> catVariants = Arrays.stream(EntityPredicateBuilder.CatType.values()).map(catType -> catType.variant.getValue()).toList();

    @Info("Get all biomes id of overworld.")
    public List<Identifier> getOverworldBiomes() {
        return overworldBiomes;
    }

    @Info("Get all biomes id of nether.")
    public List<Identifier> getNetherBiomes() {
        return netherBiomes;
    }

    @Info("Get all smithing trims id.")
    public List<Identifier> getSmithingTrims() {
        return smithingTrims;
    }

    public List<Identifier> getBreedableAnimals() {
        return BREEDABLE_ANIMALS;
    }

    public List<Identifier> getIndirectlyBreedableAnimals() {
        return INDIRECTLY_BREEDABLE_ANIMALS;
    }

    public List<Identifier> getFish() {
        return FISH;
    }

    public List<Identifier> getFishBuckets() {
        return FISH_BUCKETS;
    }

    public List<Identifier> getEdibleItems() {
        return EDIBLE_ITEMS;
    }

    public List<Identifier> getWaxScrapingTools() {
        return WAX_SCRAPING_TOOLS;
    }

    public List<Identifier> getCatVariants(){
        return catVariants;
    }
}
