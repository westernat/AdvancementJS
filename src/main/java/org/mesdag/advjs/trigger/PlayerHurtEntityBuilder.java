package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.predicate.DamagePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

class PlayerHurtEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    DamagePredicate damage;
    LootContextPredicate entity;

    @Info("The damage that was dealt.")
    public void setDamage(DamagePredicate damage) {
        this.damage = damage;
    }

    @Info("The damage that was dealt.")
    public void setDamage(Consumer<DamagePredicateBuilder> consumer) {
        DamagePredicateBuilder builder = new DamagePredicateBuilder();
        consumer.accept(builder);
        this.damage = builder.build();
    }

    @Info("The entity that was damaged.")
    public void setEntityByPredicate(EntityPredicate entity) {
        this.entity = EntityPredicate.asLootContextPredicate(entity);
    }

    @Info("The entity that was damaged.")
    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }

    @Info("The entity that was damaged.")
    public void setEntityByType(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.asLootContextPredicate(builder.build());
    }
}
