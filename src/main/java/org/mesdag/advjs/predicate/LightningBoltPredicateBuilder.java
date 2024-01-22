package org.mesdag.advjs.predicate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntitySubPredicate;
import net.minecraft.advancements.critereon.LighthingBoltPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public class LightningBoltPredicateBuilder implements EntitySetter {
    MinMaxBounds.Ints blocksSetOnFire = MinMaxBounds.Ints.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;

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

    EntitySubPredicate predicate() {
        JsonObject jsonObject = new JsonObject();
        JsonElement blocksSetOnFire = this.blocksSetOnFire.serializeToJson();
        JsonElement entityStruck = this.entityStruck.serializeToJson();
        if (!entityStruck.isJsonNull()) {
            JsonObject entity = entityStruck.getAsJsonObject();
            if (entity.get("team").isJsonNull()) {
                entity.remove("team");
            }
            jsonObject.add("entity_struck", entity);
        }
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire);
        return LighthingBoltPredicate.fromJson(jsonObject);
    }
}
