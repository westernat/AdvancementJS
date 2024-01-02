package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

public class EntityKillPlayerBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite killer = EntityPredicate.Composite.ANY;
    DamageSourcePredicate killingBlow = DamageSourcePredicate.ANY;

    @Info("""
        Checks the entity that was the source of the damage that killed the player.
        
        (for example: The skeleton that shot the arrow)
        """)
    public void setKiller(EntityPredicate killer) {
        this.killer = EntityPredicate.Composite.wrap(killer);
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
