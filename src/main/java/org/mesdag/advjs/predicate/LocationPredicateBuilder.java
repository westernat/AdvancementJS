package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

class LocationPredicateBuilder {
    final LocationPredicate.Builder builder = new LocationPredicate.Builder();

    @Info("The x position.")
    public void setX(NumberRange.FloatRange bounds) {
        builder.x(bounds);
    }

    @Info("The y position.")
    public void setY(NumberRange.FloatRange bounds) {
        builder.y(bounds);
    }

    @Info("The z position.")
    public void setZ(NumberRange.FloatRange bounds) {
        builder.z(bounds);
    }

    @Info("The biome at this location, as a resource location.")
    public void setBiome(Identifier resourceLocation) {
        builder.biome(RegistryKey.of(Registry.BIOME_KEY, resourceLocation));
    }

    @Info("The structure the entity is currently in. This tag is a resource location for a structure feature.")
    public void setStructure(Identifier resourceLocation) {
        builder.feature(RegistryKey.of(Registry.STRUCTURE_KEY, resourceLocation));
    }

    @Info("A resource location for the dimension.")
    public void setDimension(Identifier resourceLocation) {
        builder.dimension(RegistryKey.of(Registry.WORLD_KEY, resourceLocation));
    }

    @Info("The light at the location. Test fails if the location is unloaded.")
    public void setLight(LightPredicate light) {
        builder.light(light);
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlock(BlockPredicate block) {
        builder.block(block);
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluid(FluidPredicate fluid) {
        builder.fluid(fluid);
    }

    @Info("When true, success if the block is closely above a campfire or soul campfire. When false, success if not.")
    public void setSmokey(Boolean bool) {
        builder.smokey(bool);
    }

    LocationPredicate build() {
        return builder.build();
    }
}
