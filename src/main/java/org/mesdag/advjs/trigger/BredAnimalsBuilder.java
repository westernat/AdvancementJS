package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    ContextAwarePredicate parent = ContextAwarePredicate.ANY;
    ContextAwarePredicate partner = ContextAwarePredicate.ANY;
    ContextAwarePredicate child = ContextAwarePredicate.ANY;

    @Info("The parent.")
    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.wrap(parent);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.wrap(partner);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.wrap(child);
    }
}
