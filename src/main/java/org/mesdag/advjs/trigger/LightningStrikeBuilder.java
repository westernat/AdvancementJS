package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    ContextAwarePredicate lightning = ContextAwarePredicate.ANY;
    ContextAwarePredicate bystander =ContextAwarePredicate.ANY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.wrap(lightning);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.wrap(bystander);
    }
}
