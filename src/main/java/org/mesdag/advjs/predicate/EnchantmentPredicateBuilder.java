package org.mesdag.advjs.predicate;


import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.predicate.NumberRange;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

class EnchantmentPredicateBuilder {
    @Nullable
    Enchantment enchantment = null;
    NumberRange.IntRange level = NumberRange.IntRange.ANY;

    @Info("Match enchantment.")
    public void setEnchantment(Identifier location) {
        Enchantment enchantment = Registries.ENCHANTMENT.get(location);
        if (enchantment != null) {
            this.enchantment = enchantment;
        }
    }

    @Info("Match level of enchantment.")
    public void setLevel(NumberRange.IntRange bounds) {
        level = bounds;
    }
}
