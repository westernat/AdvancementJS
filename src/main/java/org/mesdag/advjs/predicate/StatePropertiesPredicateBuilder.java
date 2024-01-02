package org.mesdag.advjs.predicate;

import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;

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
                return match(pair[0].strip(), pair[1].strip());
            }
        }
        return this;
    }
}
