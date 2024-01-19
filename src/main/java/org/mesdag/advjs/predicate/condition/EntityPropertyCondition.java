package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;

public class EntityPropertyCondition implements ICondition {
    final LootCondition.Builder condition;

    public EntityPropertyCondition(EntityPredicate entityPredicate) {
        this.condition = EntityPropertiesLootCondition.builder(LootContext.EntityTarget.THIS, entityPredicate);
    }

    @Info("Invert this condition.")
    public EntityPropertyCondition invert() {
        condition.invert();
        return this;
    }

    @HideFromJS
    @Override
    public LootCondition.Builder builder() {
        return condition;
    }
}
