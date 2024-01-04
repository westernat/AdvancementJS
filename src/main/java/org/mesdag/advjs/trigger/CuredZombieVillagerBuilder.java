package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Extended zombie = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended villager = EntityPredicate.Extended.EMPTY;
    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.Extended.ofLegacy(zombie);
    }
    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Extended.ofLegacy(villager);
    }
}
