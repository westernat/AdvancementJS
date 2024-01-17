package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.loot.condition.LocationCheckLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.util.math.BlockPos;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;

import java.util.function.Consumer;

public class LocationCheck implements Check {
    LocationPredicateBuilder locationBuilder = new LocationPredicateBuilder();
    BlockPos offset = BlockPos.ORIGIN;

    @Info("The location predicate of this check.")
    public LocationCheck location(Consumer<LocationPredicateBuilder> consumer) {
        consumer.accept(this.locationBuilder);
        return this;
    }

    @Info("The offset block pos of this check.")
    public LocationCheck offset(BlockPos offset) {
        this.offset = offset;
        return this;
    }

    @HideFromJS
    @Override
    public LootCondition.Builder builder() {
        return LocationCheckLootCondition.builder(locationBuilder.getBuilder(), offset);
    }
}
