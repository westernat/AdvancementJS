package org.mesdag.advjs.trigger;

import net.minecraft.predicate.DamagePredicate;

class EntityHurtPlayerBuilder extends AbstractTriggerBuilder {
    DamagePredicate damage = DamagePredicate.ANY;

    public void setDamage(DamagePredicate damage) {
        this.damage = damage;
    }
}
