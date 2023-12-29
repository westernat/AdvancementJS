package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

class KilledByCrossbowBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite[] victims = new EntityPredicate.Composite[0];
    MinMaxBounds.Ints uniqueEntityTypes = MinMaxBounds.Ints.ANY;

    public void setVictims(EntityPredicate... victims) {
        this.victims = Stream.of(victims).map(EntityPredicate.Composite::wrap).toArray(EntityPredicate.Composite[]::new);
    }

    public void setVictims(EntityType<?>... victims) {
        this.victims = Stream.of(victims).map(this::warpEntity).toArray(EntityPredicate.Composite[]::new);
    }

    public void setUniqueEntityTypes(MinMaxBounds.Ints uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes;
    }
}
