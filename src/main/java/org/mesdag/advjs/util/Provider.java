package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.data.server.recipe.VanillaRecipeProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.source.MultiNoiseBiomeSourceParameterList;

import java.util.List;

public class Provider {
    private final List<Identifier> overworldBiomes = MultiNoiseBiomeSourceParameterList
        .Preset.OVERWORLD.biomeStream().map(RegistryKey::getRegistry).toList();
    private final List<Identifier> netherBiomes = MultiNoiseBiomeSourceParameterList
        .Preset.NETHER.biomeStream().map(RegistryKey::getRegistry).toList();
    private final List<Identifier> smithingTrims = VanillaRecipeProvider.getTrimSmithingTemplateMap().values().stream().toList();

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
}
