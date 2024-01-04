package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    EntityPredicate.Extended lightning = EntityPredicate.Extended.EMPTY;
    EntityPredicate.Extended bystander =EntityPredicate.Extended.EMPTY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.Extended.ofLegacy(lightning);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.Extended.ofLegacy(bystander);
    }
}
