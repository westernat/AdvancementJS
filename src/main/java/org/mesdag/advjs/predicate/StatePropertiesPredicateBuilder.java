package org.mesdag.advjs.predicate;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;

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
                return match(pair[0].strip(), pair[1].strip());
            }
        }
        return this;
    }
}
