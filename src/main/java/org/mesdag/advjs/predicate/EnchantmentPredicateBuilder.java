package org.mesdag.advjs.predicate;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

public class EnchantmentPredicateBuilder {
    @Nullable
    private Enchantment enchantment = null;
    private MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    public EnchantmentPredicateBuilder enchantment(ResourceLocation location) {
        Enchantment enchantment = ForgeRegistries.ENCHANTMENTS.getValue(location);
        if (enchantment != null) {
            this.enchantment = enchantment;
        }
        return this;
    }

    public EnchantmentPredicateBuilder level(MinMaxBounds.Ints bounds) {
        level = bounds;
        return this;
    }

    public EnchantmentPredicate build() {
        return new EnchantmentPredicate(enchantment, level);
    }
}
