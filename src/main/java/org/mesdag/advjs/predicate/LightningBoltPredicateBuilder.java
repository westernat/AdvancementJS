package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;

class LightningBoltPredicateBuilder {
    NumberRange.IntRange blocksSetOnFire = NumberRange.IntRange.ANY;
    EntityPredicate entityStruck = EntityPredicate.ANY;

    @Info("Test the number of blocks set on fire by this lightning bolt is between a minimum and maximum value.")
    public void blocksSetOnFire(NumberRange.IntRange bounds) {
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
        jsonobject.add("blocks_set_on_fire", blocksSetOnFire.toJson());
        jsonobject.add("entity_struck", entityStruck.toJson());
        return jsonobject;
    }
}
