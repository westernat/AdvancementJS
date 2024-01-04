package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

public class EntityKillPlayerBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended killer = EntityPredicate.Extended.EMPTY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.EMPTY;

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
        
        (for example: The skeleton that shot the arrow)
        """)
    public void setKiller(EntityPredicate killer) {
        this.killer = EntityPredicate.Extended.ofLegacy(killer);
    }

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
        
        (for example: The skeleton that shot the arrow)
        """)
    public void setKiller(EntityType<?> killer) {
        this.killer = warpEntity(killer);
    }

    @Info("Checks the type of damage that killed the player.")
    public void setKillingBlow(DamageSourcePredicate killingBlow) {
        this.killingBlow = killingBlow;
    }
}
