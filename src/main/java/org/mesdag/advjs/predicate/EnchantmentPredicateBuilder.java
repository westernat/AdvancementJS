package org.mesdag.advjs.predicate;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.predicate.NumberRange;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

class EnchantmentPredicateBuilder {
    @Nullable
    Enchantment enchantment = null;
    NumberRange.IntRange level = NumberRange.IntRange.ANY;

    public void setEnchantment(Identifier location) {
        Enchantment enchantment = Registry.ENCHANTMENT.get(location);
        if (enchantment != null) {
            this.enchantment = enchantment;
        }
    }

    public void setLevel(NumberRange.IntRange bounds) {
        level = bounds;
    }
}
