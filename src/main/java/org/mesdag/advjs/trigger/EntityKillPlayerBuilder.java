package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class EntityKillPlayerBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite killer = EntityPredicate.Composite.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    public void setKiller(EntityPredicate killer) {
        this.killer = EntityPredicate.Composite.wrap(killer);
    }

    public void setKiller(ResourceLocation killer) {
        this.killer = warpEntity(killer);
    }

    public void setKiller(EntityType<?> killer) {
        this.killer = warpEntity(killer);
    }

    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
