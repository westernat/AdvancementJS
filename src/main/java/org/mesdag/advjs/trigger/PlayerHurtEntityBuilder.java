package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.DamagePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.util.EntitySetter;

class PlayerHurtEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    DamagePredicate damage;
    LootContextPredicate entity;

    @Info("The damage that was dealt.")
    public void setDamage(DamagePredicate damage) {
        this.damage = damage;
    }

    @Info("The entity that was damaged.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.asLootContextPredicate(entity);
    }

    @Info("The entity that was damaged.")
    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
