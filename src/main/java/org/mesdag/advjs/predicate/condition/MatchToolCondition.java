package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;

import java.util.function.Consumer;

public class MatchToolCondition implements ICondition {
    final LootCondition.Builder tool;

    public MatchToolCondition(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.tool = MatchToolLootCondition.builder(builder.getBuilder());
    }

    @HideFromJS
    @Override
    public LootCondition.Builder builder() {
        return tool;
    }
}
