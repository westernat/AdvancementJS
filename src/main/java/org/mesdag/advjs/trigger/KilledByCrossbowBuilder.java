package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

class KilledByCrossbowBuilder extends AbstractTriggerBuilder implements EntitySetter {
    LootContextPredicate[] victims = new LootContextPredicate[0];
    NumberRange.IntRange uniqueEntityTypes = NumberRange.IntRange.ANY;

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void setVictims(EntityPredicate... victims) {
        this.victims = Stream.of(victims).map(EntityPredicate::asLootContextPredicate).toArray(LootContextPredicate[]::new);
    }

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void setVictims(EntityType<?>... victims) {
        this.victims = Stream.of(victims).map(this::warpEntity).toArray(LootContextPredicate[]::new);
    }

    @Info("The exact count of types of entities killed.")
    public void setUniqueEntityTypes(NumberRange.IntRange uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes;
    }
}
