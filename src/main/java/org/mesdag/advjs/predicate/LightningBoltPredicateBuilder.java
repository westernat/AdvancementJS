package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LightningBoltPredicate;

import java.util.function.Consumer;

public class LightningBoltPredicateBuilder {
    NumberRange.IntRange blocksSetOnFire = NumberRange.IntRange.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(NumberRange.IntRange bounds) {
        this.blocksSetOnFire = bounds;
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

    LightningBoltPredicate build(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire.toJson());
        jsonObject.add("entity_struck", entityStruck.toJson());
        return LightningBoltPredicate.fromJson(jsonObject);
    }
}
