package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    EntityPredicate.Extended parent = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended partner = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended child = EntityPredicate.Extended.EMPTY;

    @Info("The parent.")
    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.Extended.ofLegacy(parent);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.Extended.ofLegacy(partner);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.Extended.ofLegacy(child);
    }
}
