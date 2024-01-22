package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.FluidPredicate;
import net.minecraft.predicate.LightPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class LocationPredicateBuilder {
    final LocationPredicate.Builder builder = new LocationPredicate.Builder();

    @Info("The x position.")
    public void setX(Bounds bounds) {
        builder.x(bounds.toFloatBounds());
    }

    @Info("The y position.")
    public void setY(Bounds bounds) {
        builder.y(bounds.toFloatBounds());
    }

    @Info("The z position.")
    public void setZ(Bounds bounds) {
        builder.z(bounds.toFloatBounds());
    }

    @Info("The biome at this location, as a resource location.")
    public void setBiome(Identifier resourceLocation) {
        builder.biome(RegistryKey.of(RegistryKeys.BIOME, resourceLocation));
    }

    @Info("The structure the entity is currently in. This tag is a resource location for a structure feature.")
    public void setStructure(Identifier resourceLocation) {
        builder.feature(RegistryKey.of(RegistryKeys.STRUCTURE, resourceLocation));
    }

    @Info("A resource location for the dimension.")
    public void setDimension(Identifier resourceLocation) {
        builder.dimension(RegistryKey.of(RegistryKeys.WORLD, resourceLocation));
    }

    @Info("The light at the location. Test fails if the location is unloaded.")
    public void setLightByPredicate(LightPredicate light) {
        builder.light(light);
    }

    @Info("The light at the location. Test fails if the location is unloaded.")
    public void setLight(NumberRange.IntRange level) {
        builder.light(LightPredicate.Builder.create().light(level).build());
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlockByType(Block... blocks){
        builder.block(BlockPredicate.Builder.create().blocks(blocks).build());
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlockByPredicate(BlockPredicate block) {
        builder.block(block);
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlock(Consumer<BlockPredicateBuilder> consumer) {
        BlockPredicateBuilder builder1 = new BlockPredicateBuilder();
        consumer.accept(builder1);
        builder.block(builder1.build());
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluidByType(Fluid fluid){
        builder.fluid(FluidPredicate.Builder.create().fluid(fluid).build());
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluidByPredicate(FluidPredicate fluid) {
        builder.fluid(fluid);
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluid(Consumer<FluidPredicateBuilder> consumer) {
        FluidPredicateBuilder builder1 = new FluidPredicateBuilder();
        consumer.accept(builder1);
        builder.fluid(builder1.build());
    }

    @Info("When true, success if the block is closely above a campfire or soul campfire. When false, success if not.")
    public void setSmokey(Boolean bool) {
        builder.smokey(bool);
    }

    @HideFromJS
    public LocationPredicate build() {
        return builder.build();
    }

    @HideFromJS
    public LocationPredicate.Builder getBuilder() {
        return builder;
    }
}
