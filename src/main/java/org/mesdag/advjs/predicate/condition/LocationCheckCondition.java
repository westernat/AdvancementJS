package org.mesdag.advjs.predicate.condition;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;

import java.util.function.Consumer;

public class LocationCheckCondition implements ICondition {
    LocationPredicateBuilder locationBuilder = new LocationPredicateBuilder();
    BlockPos offset = BlockPos.ZERO;

    @Info("The location predicate of this check.")
    public LocationCheckCondition location(Consumer<LocationPredicateBuilder> consumer) {
        consumer.accept(this.locationBuilder);
        return this;
    }

    @Info("The offset block pos of this check.")
    public LocationCheckCondition offset(BlockPos offset) {
        this.offset = offset;
        return this;
    }

    @HideFromJS
    @Override
    public LootItemCondition.Builder builder() {
        return LocationCheck.checkLocation(locationBuilder.getBuilder(), offset);
    }
}
