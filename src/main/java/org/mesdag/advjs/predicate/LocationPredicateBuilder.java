package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

class LocationPredicateBuilder {
    final LocationPredicate.Builder builder = new LocationPredicate.Builder();

    @Info("The x position.")
    public void setX(MinMaxBounds.Doubles bounds) {
        builder.setX(bounds);
    }

    @Info("The y position.")
    public void setY(MinMaxBounds.Doubles bounds) {
        builder.setY(bounds);
    }

    @Info("The z position.")
    public void setZ(MinMaxBounds.Doubles bounds) {
        builder.setZ(bounds);
    }

    @Info("The biome at this location, as a resource location.")
    public void setBiome(ResourceLocation resourceLocation) {
        builder.setBiome(ResourceKey.create(Registries.BIOME, resourceLocation));
    }

    @Info("The structure the entity is currently in. This tag is a resource location for a structure feature.")
    public void setStructure(ResourceLocation resourceLocation) {
        builder.setStructure(ResourceKey.create(Registries.STRUCTURE, resourceLocation));
    }

    @Info("A resource location for the dimension.")
    public void setDimension(ResourceLocation resourceLocation) {
        builder.setDimension(ResourceKey.create(Registries.DIMENSION, resourceLocation));
    }

    @Info("The light at the location. Test fails if the location is unloaded.")
    public void setLight(LightPredicate light) {
        builder.setLight(light);
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlock(BlockPredicate block) {
        builder.setBlock(block);
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluid(FluidPredicate fluid) {
        builder.setFluid(fluid);
    }

    @Info("When true, success if the block is closely above a campfire or soul campfire. When false, success if not.")
    public void setSmokey(Boolean bool) {
        builder.setSmokey(bool);
    }

    LocationPredicate build() {
        return builder.build();
    }
}
