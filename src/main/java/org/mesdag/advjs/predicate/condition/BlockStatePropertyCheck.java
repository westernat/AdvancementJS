package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
    public LootItemCondition.Builder builder() {
        if (matchState) {
            return new LootItemBlockStatePropertyCondition.Builder(block).setProperties(stateProperties.getBuilder());
        } else {
            return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block);
        }
    }
}
