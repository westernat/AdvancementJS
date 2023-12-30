package org.mesdag.advjs.util;

import com.google.common.collect.ImmutableSet;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

public interface ItemSetter {
    default ItemPredicate warpItem(Ingredient ingredient) {
        ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
        for (ItemStack itemStack : ingredient.getItems()) {
            builder.add(itemStack.getItem());
        }
        return new ItemPredicate(
            null,
            builder.build(),
            MinMaxBounds.Ints.ANY,
            MinMaxBounds.Ints.ANY,
            EnchantmentPredicate.NONE,
            EnchantmentPredicate.NONE,
            null,
            NbtPredicate.ANY
        );
    }
}
