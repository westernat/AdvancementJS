package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.mesdag.advjs.AdvJS;

class StatePropertiesPredicateBuilder {
    final StatePropertiesPredicate.Builder builder = StatePropertiesPredicate.Builder.properties();

    public StatePropertiesPredicateBuilder match(String key, String value) {
        Property<Boolean> property = BooleanProperty.create(key);
        builder.hasProperty(property, value);
        return this;
    }

    public StatePropertiesPredicateBuilder matchAll(String pairsString) {
        String[] pairStrings = pairsString.split(",");
        for (String pairString : pairStrings) {
            String[] pair = pairString.split("=");
            if (pair.length == 2) {
                match(pair[0].strip(), pair[1].strip());
            } else {
                AdvJS.LOGGER.warn("Find a worse pair '" + pairString + "'");
            }
        }
        return this;
    }

    public StatePropertiesPredicateBuilder matchAll(JsonArray jsonArray) {
        jsonArray.forEach(element -> {
            JsonObject state = GsonHelper.convertToJsonObject(element, "state");
            if (state.has("key") && state.has("value")) {
                match(state.get("key").getAsString(), state.get("value").getAsString());
            } else {
                AdvJS.LOGGER.warn("Find a worse pair '" + state + "'");
            }
        });
        return this;
    }
}
