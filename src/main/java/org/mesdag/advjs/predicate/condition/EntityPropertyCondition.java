package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;

public class EntityPropertyCondition implements ICondition {
    final LootItemCondition.Builder condition;

    @HideFromJS
    public EntityPropertyCondition(EntityPredicate entityPredicate) {
        this.condition = LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, entityPredicate);
    }

    @Info("Invert this condition.")
    public EntityPropertyCondition invert() {
        condition.invert();
        return this;
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return condition;
    }
}
