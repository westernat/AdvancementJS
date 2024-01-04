package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantment;

import javax.annotation.Nullable;

class EnchantmentPredicateBuilder {
    @Nullable
    Enchantment enchantment = null;
    MinMaxBounds.Ints level = MinMaxBounds.Ints.ANY;

    @Info("Match enchantment.")
    public void setEnchantment(ResourceLocation location) {
        Enchantment enchantment = Registry.ENCHANTMENT.get(location);
        if (enchantment != null) {
            this.enchantment = enchantment;
        }
    }

    @Info("Match level of enchantment.")
    public void setLevel(MinMaxBounds.Ints bounds) {
        level = bounds;
    }
}
