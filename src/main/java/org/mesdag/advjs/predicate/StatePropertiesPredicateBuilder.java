package org.mesdag.advjs.predicate;

import net.minecraft.predicate.StatePredicate;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;

class StatePropertiesPredicateBuilder {
    final StatePredicate.Builder builder = StatePredicate.Builder.create();

    public StatePropertiesPredicateBuilder matchProperty(String key, String value) {
        Property<Boolean> property = BooleanProperty.of(key);
        builder.exactMatch(property, value);
        return this;
    }
}
