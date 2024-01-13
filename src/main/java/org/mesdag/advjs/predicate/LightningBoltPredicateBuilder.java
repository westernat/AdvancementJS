package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.LighthingBoltPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class LightningBoltPredicateBuilder {
    MinMaxBounds.Ints blocksSetOnFire = MinMaxBounds.Ints.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(Bounds bounds) {
        this.blocksSetOnFire = bounds.toIntegerBounds();
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void entityStruck(EntityPredicate entityPredicate) {
        this.entityStruck = entityPredicate;
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void entityStruck(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entityStruck = builder.build();
    }

    LighthingBoltPredicate build(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire.serializeToJson());
        jsonObject.add("entity_struck", entityStruck.serializeToJson());
        return LighthingBoltPredicate.fromJson(jsonObject);
    }
}
