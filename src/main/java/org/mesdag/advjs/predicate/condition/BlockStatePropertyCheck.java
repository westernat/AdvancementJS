package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.block.Block;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import org.mesdag.advjs.predicate.StatePropertiesPredicateBuilder;

import java.util.function.Consumer;

public class BlockStatePropertyCheck implements Check {
    final Block block;
    private boolean matchState = false;
    StatePropertiesPredicateBuilder stateProperties = new StatePropertiesPredicateBuilder();

    @HideFromJS
    public BlockStatePropertyCheck(Block block) {
        this.block = block;
    }

    public BlockStatePropertyCheck stateProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        consumer.accept(this.stateProperties);
        this.matchState = true;
        return this;
    }

    @HideFromJS
    @Override
    public LootCondition.Builder builder() {
        if (matchState) {
            return new BlockStatePropertyLootCondition.Builder(block).properties(stateProperties.getBuilder());
        } else {
            return BlockStatePropertyLootCondition.builder(block);
        }
    }
}
