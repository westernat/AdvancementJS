package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.MultiNoiseBiomeSourceParameterList;

import java.util.List;

public class Provider {
    private static final List<ResourceLocation> overworldBiomes = MultiNoiseBiomeSourceParameterList
        .Preset.OVERWORLD.usedBiomes().map(ResourceKey::location).toList();
    private static final List<ResourceLocation> smithingTrims = VanillaRecipeProvider.smithingTrims().values().stream().toList();

    @Info("Get all biomes id of overworld.")
    public List<ResourceLocation> getOverworldBiomes() {
        return overworldBiomes;
    }

    @Info("Get all smithing trims id.")
    public List<ResourceLocation> getSmithingTrims() {
        return smithingTrims;
    }
}
