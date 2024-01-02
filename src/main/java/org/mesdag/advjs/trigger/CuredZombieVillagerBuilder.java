package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Composite zombie = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite villager = EntityPredicate.Composite.ANY;

    @Info("""
        The zombie villager right before the conversion is complete (not when it is initiated).
                
        The 'type' tag is redundant since it will always be 'zombie_villager'.
        """)
    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.Composite.wrap(zombie);
    }

    @Info("""
        The villager that is the result of the conversion.
        
        The 'type' tag is redundant since it will always be 'villager'.
        """)
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Composite.wrap(villager);
    }
}
