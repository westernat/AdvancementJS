package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite killed = EntityPredicate.Composite.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    public void setKilled(EntityPredicate killed) {
        this.killed = EntityPredicate.Composite.wrap(killed);
    }

    public void setKilled(ResourceLocation killed) {
        this.killed = warpEntity(killed);
    }

    public void setKilled(EntityType<?> killed) {
        this.killed = warpEntity(killed);
    }

    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
