package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Composite parent = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite partner = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite child = EntityPredicate.Composite.ANY;

    @Info("The parent.")
    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.Composite.wrap(parent);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.Composite.wrap(partner);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.Composite.wrap(child);
    }
}
