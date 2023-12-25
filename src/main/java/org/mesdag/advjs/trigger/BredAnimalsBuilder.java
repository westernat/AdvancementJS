package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Composite parent = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite partner = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite child = EntityPredicate.Composite.ANY;

    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.Composite.wrap(parent);
    }

    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.Composite.wrap(partner);
    }

    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.Composite.wrap(child);
    }
}
