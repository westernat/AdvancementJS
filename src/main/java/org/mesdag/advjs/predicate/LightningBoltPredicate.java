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

    // TODO fromJson

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
