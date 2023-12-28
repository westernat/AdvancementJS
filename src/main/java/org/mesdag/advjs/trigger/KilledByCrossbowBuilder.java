package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

class KilledByCrossbowBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended[] victims = new EntityPredicate.Extended[0];
    NumberRange.IntRange uniqueEntityTypes = NumberRange.IntRange.ANY;

    public void setVictims(EntityPredicate... victims) {
        this.victims = Stream.of(victims).map(EntityPredicate.Extended::ofLegacy).toArray(EntityPredicate.Extended[]::new);
    }

    public void setVictims(Identifier... victims) {
        this.victims = Stream.of(victims).map(this::warpEntity).toArray(EntityPredicate.Extended[]::new);
    }

    public void setVictims(EntityType<?>... victims) {
        this.victims = Stream.of(victims).map(this::warpEntity).toArray(EntityPredicate.Extended[]::new);
    }

    public void setUniqueEntityTypes(NumberRange.IntRange uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes;
    }
}
