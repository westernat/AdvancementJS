package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Composite zombie = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite villager = EntityPredicate.Composite.ANY;

    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.Composite.wrap(zombie);
    }

    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Composite.wrap(villager);
    }
}
