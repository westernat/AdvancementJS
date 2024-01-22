package org.mesdag.advjs.predicate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.LightningBoltPredicate;
import net.minecraft.predicate.entity.*;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public class LightningBoltPredicateBuilder implements EntitySetter {
    NumberRange.IntRange blocksSetOnFire = NumberRange.IntRange.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;
    DistancePredicate distance = DistancePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    NbtPredicate nbt = NbtPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(Bounds bounds) {
        this.blocksSetOnFire = bounds.toIntegerBounds();
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void setEntityStruckByType(EntityType<?> entityType) {
        this.entityStruck = toEntity(entityType);
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void setEntityStruckByPredicate(EntityPredicate entityPredicate) {
        this.entityStruck = entityPredicate;
    }

    @Info("Test the properties of entities struck by this lightning bolt.")
    public void setEntityStruck(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.entityStruck = builder.build();
    }

    TypeSpecificPredicate predicate() {
        JsonObject jsonObject = new JsonObject();
        JsonElement blocksSetOnFire = this.blocksSetOnFire.toJson();
        JsonElement entityStruck = this.entityStruck.toJson();
        if (!entityStruck.isJsonNull()) {
            JsonObject entity = entityStruck.getAsJsonObject();
            if (entity.get("team").isJsonNull()) {
                entity.remove("team");
            }
            jsonObject.add("entity_struck", entity);
        }
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire);
        return LightningBoltPredicate.fromJson(jsonObject);
    }
}
