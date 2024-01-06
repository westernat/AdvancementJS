package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

public class EntityKillPlayerBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended killer = EntityPredicate.Extended.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

    public void setKiller(EntityPredicate killer) {
        this.killer = EntityPredicate.Extended.ofLegacy(killer);
    }

    public void setKiller(EntityType<?> killer) {
        this.killer = warpEntity(killer);
    }

    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
