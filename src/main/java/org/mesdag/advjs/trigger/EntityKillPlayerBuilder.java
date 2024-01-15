package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public class EntityKillPlayerBuilder extends AbstractTriggerBuilder implements EntitySetter {
    LootContextPredicate killer = LootContextPredicate.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
        
        (for example: The skeleton that shot the arrow)
        """)
    public void setKillerByPredicate(EntityPredicate killer) {
        this.killer = EntityPredicate.asLootContextPredicate(killer);
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
                
        (for example: The skeleton that shot the arrow)
        """)
    public void setKiller(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.killer = EntityPredicate.asLootContextPredicate(builder.build());
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
        
        (for example: The skeleton that shot the arrow)
        """)
    public void setKillerByType(EntityType<?> killer) {
        this.killer = warpEntity(killer);
    }

    @Info("Checks the type of damage that killed the player.")
    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }

    @Info("Checks the type of damage that killed the player.")
    public void setKillingBlow(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        this.killingBlow = builder.build();
    }
}
