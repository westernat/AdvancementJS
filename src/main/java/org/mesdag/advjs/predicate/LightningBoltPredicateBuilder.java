package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;

class LightningBoltPredicateBuilder {
    MinMaxBounds.Ints blocksSetOnFire = MinMaxBounds.Ints.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(MinMaxBounds.Ints bounds) {
        this.blocksSetOnFire = bounds;
    }

    @Info("""
        Test the properties of entities struck by this lightning bolt.
        
        Passes if at least one of the struck entities matches the entered conditions.
        """)
    public void entityStruck(EntityPredicate entityPredicate) {
        this.entityStruck = entityPredicate;
    }

    JsonObject toJson() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("blocks_set_on_fire", blocksSetOnFire.serializeToJson());
        jsonobject.add("entity_struck", entityStruck.serializeToJson());
        return jsonobject;
    }
}
