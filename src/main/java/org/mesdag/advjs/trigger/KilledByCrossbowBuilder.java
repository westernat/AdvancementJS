package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.Bounds;

import java.util.ArrayList;
import java.util.function.Consumer;

public class KilledByCrossbowBuilder extends BaseTriggerInstanceBuilder {
    final ArrayList<LootContextPredicate> victims = new ArrayList<>();
    NumberRange.IntRange uniqueEntityTypes = NumberRange.IntRange.ANY;

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void addVictimByPredicate(EntityPredicate victim) {
        this.victims.add(wrapEntity(victim));
    }

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void addVictim(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.victims.add(wrapEntity(builder.build()));
    }

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void addVictimByType(EntityType<?> victim) {
        this.victims.add(wrapEntity(victim));
    }

    @Info("A list of victims. All of the entries must be matched, and one killed entity may match only one entry.")
    public void addVictimByCondition(ICondition... conditions) {
        this.victims.add(wrapEntity(conditions));
    }

    @Info("The exact count of types of entities killed.")
    public void setUniqueEntityTypes(Bounds uniqueEntityTypes) {
        this.uniqueEntityTypes = uniqueEntityTypes.toIntegerBounds();
    }
}
