package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import org.mesdag.advjs.util.Bounds;

import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "unused"})
public class EnchantmentPredicateBuilder {
    @Nullable
    Enchantment enchantment = null;
    MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    @Info("Match enchantment.")
    public void setEnchantment(ResourceLocation location) {
        Enchantment enchantment = BuiltInRegistries.ENCHANTMENT.get(location);
        if (enchantment != null) {
            this.enchantment = enchantment;
        }
    }

    @Info("Match level of enchantment.")
    public void setLevel(Bounds bounds) {
        this.level = bounds.toIntegerBounds();
    }

    EnchantmentPredicate build() {
        return new EnchantmentPredicate(enchantment, level);
    }
}
