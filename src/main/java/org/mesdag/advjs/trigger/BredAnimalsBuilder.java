package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Extended parent = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended partner = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended child = EntityPredicate.Extended.EMPTY;

    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.Extended.ofLegacy(parent);
    }

    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.Extended.ofLegacy(partner);
    }

    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.Extended.ofLegacy(child);
    }
}
