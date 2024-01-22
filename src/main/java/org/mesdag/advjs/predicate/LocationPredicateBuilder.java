package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.BlockPredicate;
import net.minecraft.advancements.critereon.FluidPredicate;
import net.minecraft.advancements.critereon.LightPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class LocationPredicateBuilder {
    final LocationPredicate.Builder builder = new LocationPredicate.Builder();

    @Info("The x position.")
    public void setX(Bounds bounds) {
        builder.setX(bounds.toDoubleBounds());
    }

    @Info("The y position.")
    public void setY(Bounds bounds) {
        builder.setY(bounds.toDoubleBounds());
    }

    @Info("The z position.")
    public void setZ(Bounds bounds) {
        builder.setZ(bounds.toDoubleBounds());
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
    public void setLightByPredicate(LightPredicate light) {
        builder.setLight(light);
    }

    @Info("The light at the location. Test fails if the location is unloaded.")
    public void setLight(Bounds level) {
        builder.setLight(LightPredicate.Builder.light().setComposite(level.toIntegerBounds()).build());
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlockByType(Block... blocks){
        builder.setBlock(BlockPredicate.Builder.block().of(blocks).build());
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlockByPredicate(BlockPredicate block) {
        builder.setBlock(block);
    }

    @Info("The block at the location. Test fails if the location is unloaded.")
    public void setBlock(Consumer<BlockPredicateBuilder> consumer) {
        BlockPredicateBuilder builder1 = new BlockPredicateBuilder();
        consumer.accept(builder1);
        builder.setBlock(builder1.build());
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluidByType(Fluid fluid){
        builder.setFluid(FluidPredicate.Builder.fluid().of(fluid).build());
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluidByPredicate(FluidPredicate fluid) {
        builder.setFluid(fluid);
    }

    @Info("The fluid at the location. Test fails if the location is unloaded.")
    public void setFluid(Consumer<FluidPredicateBuilder> consumer) {
        FluidPredicateBuilder builder1 = new FluidPredicateBuilder();
        consumer.accept(builder1);
        builder.setFluid(builder1.build());
    }

    @Info("When true, success if the block is closely above a campfire or soul campfire. When false, success if not.")
    public void setSmokey(boolean bool) {
        builder.setSmokey(bool);
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
