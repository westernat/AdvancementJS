package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class LightningBoltPredicateBuilder {
    MinMaxBounds.Ints blocksSetOnFire = MinMaxBounds.Ints.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    NbtPredicate nbt = NbtPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(Bounds bounds) {
        this.blocksSetOnFire = bounds.toIntegerBounds();
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void entityStruck(EntityPredicate entityPredicate) {
        this.entityStruck = entityPredicate;
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void entityStruck(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entityStruck = builder.build();
    }

    // TODO info
    public void distance(DistancePredicate distance) {
        this.distance = distance;
    }

    public void distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        this.distance = builder.build();
    }

    public void location(LocationPredicate location) {
        this.location = location;
    }

    public void location(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        this.location = builder1.build();
    }

    public void nbt(CompoundTag nbt) {
        this.nbt = new NbtPredicate(nbt);
    }

    @HideFromJS
    public LightningBoltPredicate predicate() {
        return new LightningBoltPredicate(blocksSetOnFire, entityStruck, distance, location, nbt);
    }
}
