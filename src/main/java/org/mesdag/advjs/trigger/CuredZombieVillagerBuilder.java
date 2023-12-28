package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;

class CuredZombieVillagerBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Extended zombie = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended villager = EntityPredicate.Extended.EMPTY;

    public void setZombie(EntityPredicate zombie) {
        this.zombie = EntityPredicate.Extended.ofLegacy(zombie);
    }

    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Extended.ofLegacy(villager);
    }
}
