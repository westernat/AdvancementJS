package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

class KilledByCrossbowBuilder extends AbstractTriggerBuilder implements EntitySetter {
    ContextAwarePredicate[] victims = new ContextAwarePredicate[0];
    MinMaxBounds.Ints uniqueEntityTypes = MinMaxBounds.Ints.ANY;

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void setVictimsByPredicate(EntityPredicate... victims) {
        this.victims = Stream.of(victims).map(EntityPredicate::wrap).toArray(ContextAwarePredicate[]::new);
    }

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void setVictimsByType(EntityType<?>... victims) {
        this.victims = Stream.of(victims).map(this::warpEntity).toArray(ContextAwarePredicate[]::new);
    }

    @Info("The exact count of types of entities killed.")
    public void setUniqueEntityTypes(Bounds uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes.toIntegerBounds();
    }
}
