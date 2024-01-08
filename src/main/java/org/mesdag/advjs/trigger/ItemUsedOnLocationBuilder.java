package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.predicate.LocationPredicateBuilder;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

class ItemUsedOnLocationBuilder extends AbstractTriggerBuilder implements BlockSetter, ItemSetter {
    LocationPredicateBuilder locationBuilder = new LocationPredicateBuilder();
    ItemPredicateBuilder toolBuilder = new ItemPredicateBuilder();

    @Info("The location of the block placed.")
    public void setLocation(Consumer<LocationPredicateBuilder> consumer) {
        consumer.accept(locationBuilder);
    }

    @Info("The tool is the item used to place the block.")
    public void setTool(Consumer<ItemPredicateBuilder> consumer) {
        consumer.accept(toolBuilder);
    }

    LocationPredicate.Builder getLPB(){
        return locationBuilder.getBuilder();
    }

    ItemPredicate.Builder getIPB(){
        return toolBuilder.getBuilder();
    }
}
