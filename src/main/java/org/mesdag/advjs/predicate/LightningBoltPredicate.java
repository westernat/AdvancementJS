package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;

public class LightningBoltPredicate {
    public static final LightningBoltPredicate ANY = new LightningBoltPredicate(NumberRange.IntRange.ANY, EntityPredicate.ANY, DistancePredicate.ANY, LocationPredicate.ANY, NbtPredicate.ANY);

    private final NumberRange.IntRange blocksSetOnFire;
    private final EntityPredicate entityStruck;
    private final DistancePredicate distance;
    private final LocationPredicate location;
    private final NbtPredicate nbt;

    LightningBoltPredicate(NumberRange.IntRange blocksSetOnFire, EntityPredicate entityStruck, DistancePredicate distance, LocationPredicate location, NbtPredicate nbt) {
        this.blocksSetOnFire = blocksSetOnFire;
        this.entityStruck = entityStruck;
        this.distance = distance;
        this.location = location;
        this.nbt = nbt;
    }

    // TODO fromJson

    @HideFromJS
    public LootContextPredicate build() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("blocks_set_on_fire", blocksSetOnFire.toJson());
        JsonObject entity = entityStruck.toJson().getAsJsonObject();
        if (entity.get("team").isJsonNull()) {
            entity.remove("team");
        }
        jsonObject.add("entity_struck", entity);
        return EntityPredicate.asLootContextPredicate(EntityPredicate.Builder.create()
            .typeSpecific(net.minecraft.predicate.entity.LightningBoltPredicate.fromJson(jsonObject))
            .distance(distance)
            .location(location)
            .nbt(nbt)
            .build()
        );
    }
}
