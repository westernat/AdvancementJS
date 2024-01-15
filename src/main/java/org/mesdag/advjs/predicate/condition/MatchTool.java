package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;

import java.util.function.Consumer;

public class MatchTool implements Check {
    final LootCondition.Builder tool;

    @HideFromJS
    public MatchTool(Consumer<ItemPredicateBuilder> consumer) {
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
