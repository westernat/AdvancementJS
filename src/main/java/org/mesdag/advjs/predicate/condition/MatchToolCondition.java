package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;

import java.util.function.Consumer;

public class MatchToolCondition implements ICondition {
    final LootItemCondition.Builder tool;

    public MatchToolCondition(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.tool = MatchTool.toolMatches(builder.getBuilder());
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return tool;
    }
}
