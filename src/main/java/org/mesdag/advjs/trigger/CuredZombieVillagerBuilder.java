package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;

import java.util.function.Consumer;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    LootContextPredicate zombie = LootContextPredicate.EMPTY;
    LootContextPredicate villager = LootContextPredicate.EMPTY;
    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.asLootContextPredicate(zombie);
    }

    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.zombie = EntityPredicate.asLootContextPredicate(builder.build());
    }

    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.asLootContextPredicate(villager);
    }

    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.villager = EntityPredicate.asLootContextPredicate(builder.build());
    }
}
