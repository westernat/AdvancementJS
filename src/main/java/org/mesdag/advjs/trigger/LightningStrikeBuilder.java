package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    EntityPredicate.Extended lightning = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended bystander =EntityPredicate.Extended.EMPTY;

    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.Extended.ofLegacy(lightning);
    }

    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.Extended.ofLegacy(bystander);
    }
}
