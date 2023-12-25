package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    EntityPredicate.Composite lightning = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite bystander =EntityPredicate.Composite.ANY;

    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.Composite.wrap(lightning);
    }

    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.Composite.wrap(bystander);
    }
}
