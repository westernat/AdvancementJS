package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ChanneledLightningBuilder extends BaseTriggerInstanceBuilder {
    final ArrayList<LootContextPredicate> victims = new ArrayList<>();

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void addVictimByPredicate(EntityPredicate entityPredicate) {
        this.victims.add(wrapEntity(entityPredicate));
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void addVictimByType(EntityType<?> entityTypes) {
        this.victims.add(wrapEntity(entityTypes));
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void addVictimByType(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.victims.add(wrapEntity(builder.build()));
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void addVictimByCondition(ICondition... conditions) {
        this.victims.add(wrapEntity(conditions));
    }
}
