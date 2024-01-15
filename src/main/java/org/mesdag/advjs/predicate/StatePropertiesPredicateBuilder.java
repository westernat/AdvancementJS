package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public class StatePropertiesPredicateBuilder {
    final StatePropertiesPredicate.Builder builder = StatePropertiesPredicate.Builder.properties();

    @Info(value = "Match one block state.",
        params = {
            @Param(name = "key", value = "Name of block state"),
            @Param(name = "value", value = "Value of block state")
        })
    public StatePropertiesPredicateBuilder match(String key, String value) {
        builder.hasProperty(BooleanProperty.create(key), value);
        return this;
    }

    @Info("""
        Match all block states.
                
        Accept a string like 'state1=true,state2=15'.
        """)
    public StatePropertiesPredicateBuilder matchAll(String pairsString) {
        String[] pairStrings = pairsString.split(",");
        for (String pairString : pairStrings) {
            String[] pair = pairString.split("=");
            if (pair.length == 2) {
                match(pair[0].strip(), pair[1].strip());
            } else {
                ConsoleJS.SERVER.warn("Find a worse pair '" + pairString + "'");
            }
        }
        return this;
    }

    @HideFromJS
    public StatePropertiesPredicate build() {
        return builder.build();
    }

    @HideFromJS
    public StatePropertiesPredicate.Builder getBuilder() {
        return builder;
    }
}
