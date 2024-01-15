package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;

import java.util.function.Consumer;

public class LocationCheck implements Check {
    LocationPredicateBuilder locationBuilder = new LocationPredicateBuilder();
    BlockPos offset = BlockPos.ZERO;

    public LocationCheck location(Consumer<LocationPredicateBuilder> consumer) {
        consumer.accept(this.locationBuilder);
        return this;
    }

    public LocationCheck offset(BlockPos offset) {
        this.offset = offset;
        return this;
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return net.minecraft.world.level.storage.loot.predicates.LocationCheck.checkLocation(locationBuilder.getBuilder(), offset);
    }
}
