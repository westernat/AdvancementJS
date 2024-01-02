package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    EntityPredicate.Composite lightning = EntityPredicate.Composite.ANY;
    EntityPredicate.Composite bystander =EntityPredicate.Composite.ANY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.Composite.wrap(lightning);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.Composite.wrap(bystander);
    }
}
