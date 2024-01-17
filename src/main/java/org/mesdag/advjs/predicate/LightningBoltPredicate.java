package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.*;

public class LightningBoltPredicate {
    public static final LightningBoltPredicate ANY = new LightningBoltPredicate(MinMaxBounds.Ints.ANY, EntityPredicate.ANY, DistancePredicate.ANY, LocationPredicate.ANY, NbtPredicate.ANY);

    private final MinMaxBounds.Ints blocksSetOnFire;
    private final EntityPredicate entityStruck;
    private final DistancePredicate distance;
    private final LocationPredicate location;
    private final NbtPredicate nbt;

    LightningBoltPredicate(MinMaxBounds.Ints blocksSetOnFire, EntityPredicate entityStruck, DistancePredicate distance, LocationPredicate location, NbtPredicate nbt) {
        this.blocksSetOnFire = blocksSetOnFire;
        this.entityStruck = entityStruck;
        this.distance = distance;
        this.location = location;
        this.nbt = nbt;
    }

    static LightningBoltPredicate fromJson(JsonObject jsonObject) {
        MinMaxBounds.Ints blockSetOnFire = jsonObject.has("blocks_set_on_fire") ? MinMaxBounds.Ints.fromJson(jsonObject.get("blocks_set_on_fire")) : MinMaxBounds.Ints.ANY;
        EntityPredicate entityStruck = jsonObject.has("entity_struck") ? EntityPredicate.fromJson(jsonObject.get("entity_struck")) : EntityPredicate.ANY;
        DistancePredicate distance = jsonObject.has("distance") ? DistancePredicate.fromJson(jsonObject.get("distance")) : DistancePredicate.ANY;
        LocationPredicate location = jsonObject.has("location") ? LocationPredicate.fromJson(jsonObject.get("location")) : LocationPredicate.ANY;
        NbtPredicate nbt = jsonObject.has("nbt") ? NbtPredicate.fromJson(jsonObject.get("nbt")) : NbtPredicate.ANY;
        return new LightningBoltPredicate(blockSetOnFire, entityStruck, distance, location, nbt);
    }

    @HideFromJS
    public ContextAwarePredicate build() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire.serializeToJson());
        JsonObject entity = entityStruck.serializeToJson().getAsJsonObject();
        if (entity.get("team").isJsonNull()) {
            entity.remove("team");
        }
        jsonObject.add("entity_struck", entity);
        return EntityPredicate.wrap(EntityPredicate.Builder.entity()
            .subPredicate(LighthingBoltPredicate.fromJson(jsonObject))
            .distance(distance)
            .located(location)
            .nbt(nbt)
            .build()
        );
    }
}
