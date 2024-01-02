package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.DamagePredicate;

class EntityHurtPlayerBuilder extends AbstractTriggerBuilder {
    DamagePredicate damage = DamagePredicate.ANY;

    @Info("Checks the damage done to the player.")
    public void setDamage(DamagePredicate damage) {
        this.damage = damage;
    }
}
