package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.EntitySetter;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended killed = EntityPredicate.Extended.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

    public void setKilled(EntityPredicate killed) {
        this.killed = EntityPredicate.Extended.ofLegacy(killed);
    }

    public void setKilled(Identifier killed) {
        this.killed = warpEntity(killed);
    }

    public void setKilled(EntityType<?> killed) {
        this.killed = warpEntity(killed);
    }

    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
