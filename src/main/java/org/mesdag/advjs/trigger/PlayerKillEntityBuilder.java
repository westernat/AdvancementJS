package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.util.EntitySetter;

class PlayerKillEntityBuilder extends AbstractTriggerBuilder implements EntitySetter {
    LootContextPredicate killed = LootContextPredicate.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

    @Info("The entity that was killed.")
    public void setKilled(EntityPredicate killed) {
        this.killed = EntityPredicate.asLootContextPredicate(killed);
    }

    @Info("The entity that was killed.")
    public void setKilled(EntityType<?> killed) {
        this.killed = warpEntity(killed);
    }

    @Info("The type of damage that killed an entity.")
    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
