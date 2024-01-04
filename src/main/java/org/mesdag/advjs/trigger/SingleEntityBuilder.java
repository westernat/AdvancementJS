package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

class SingleEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.wrap(entity);
    }

    public void setEntity(EntityType<?> entity) {
        this.entity = warpEntity(entity);
    }
}
