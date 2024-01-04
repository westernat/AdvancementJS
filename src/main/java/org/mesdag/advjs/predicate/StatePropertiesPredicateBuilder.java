package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.mesdag.advjs.AdvJS;

class StatePropertiesPredicateBuilder {
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
                return match(pair[0].strip(), pair[1].strip());
            } else {
                AdvJS.LOGGER.warn("Find a worse pair '" + pairString + "'");
            }
        }
        return this;
    }
}
