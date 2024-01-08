package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;

class BredAnimalsBuilder extends AbstractTriggerBuilder {
    LootContextPredicate parent = LootContextPredicate.EMPTY;
    LootContextPredicate partner = LootContextPredicate.EMPTY;
    LootContextPredicate child = LootContextPredicate.EMPTY;

    @Info("The parent.")
    public void setParent(EntityPredicate parent) {
        this.parent = EntityPredicate.asLootContextPredicate(parent);
    }

    @Info("The partner (The entity the parent was bred with).")
    public void setPartner(EntityPredicate partner) {
        this.partner = EntityPredicate.asLootContextPredicate(partner);
    }

    @Info("Checks properties of the child that results from the breeding.")
    public void setChild(EntityPredicate child) {
        this.child = EntityPredicate.asLootContextPredicate(child);
    }
}
