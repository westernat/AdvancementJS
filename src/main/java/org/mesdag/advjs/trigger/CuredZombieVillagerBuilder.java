package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.function.Consumer;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate zombie = ContextAwarePredicate.ANY;
    ContextAwarePredicate villager = ContextAwarePredicate.ANY;

    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.wrap(zombie);
    }

    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.zombie = EntityPredicate.wrap(builder.build());
    }

    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.wrap(villager);
    }

    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.villager = EntityPredicate.wrap(builder.build());
    }
}
