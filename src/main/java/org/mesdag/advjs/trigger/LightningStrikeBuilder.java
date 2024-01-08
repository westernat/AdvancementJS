package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;

class LightningStrikeBuilder extends AbstractTriggerBuilder{
    LootContextPredicate lightning = LootContextPredicate.EMPTY;
    LootContextPredicate bystander =LootContextPredicate.EMPTY;

    @Info("The lightning bolt that disappeared.")
    public void setLightning(EntityPredicate lightning) {
        this.lightning = EntityPredicate.asLootContextPredicate(lightning);
    }

    @Info("An entity not hurt by the lightning strike but in a certain area around it.")
    public void setBystander(EntityPredicate bystander) {
        this.bystander = EntityPredicate.asLootContextPredicate(bystander);
    }
}
