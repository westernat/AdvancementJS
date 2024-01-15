package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;

import java.util.function.Consumer;

public class MatchTool implements Check {
    final LootItemCondition.Builder tool;

    @HideFromJS
    public MatchTool(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.tool = net.minecraft.world.level.storage.loot.predicates.MatchTool.toolMatches(builder.getBuilder());
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return tool;
    }
}
