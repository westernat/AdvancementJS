package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.JsonHelper;
import org.mesdag.advjs.AdvJS;

class StatePropertiesPredicateBuilder {
    final StatePredicate.Builder builder = StatePredicate.Builder.create();

    public StatePropertiesPredicateBuilder match(String key, String value) {
        Property<Boolean> property = BooleanProperty.of(key);
        builder.exactMatch(property, value);
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
            JsonObject state = JsonHelper.asObject(element, "state");
            if (state.has("key") && state.has("value")) {
                match(state.get("key").getAsString(), state.get("value").getAsString());
            } else {
                AdvJS.LOGGER.warn("Find a worse pair '" + state + "'");
            }
        });
        return this;
    }
}
