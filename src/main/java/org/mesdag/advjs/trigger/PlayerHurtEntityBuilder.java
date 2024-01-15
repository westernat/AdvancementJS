package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DamagePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.predicate.DamagePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

class PlayerHurtEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    DamagePredicate damage = DamagePredicate.ANY;
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

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
        this.entity = EntityPredicate.wrap(entity);
    }

    @Info("The entity that was damaged.")
    public void setEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entity = EntityPredicate.wrap(builder.build());
    }

    @Info("The entity that was damaged.")
    public void setEntityByType(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
