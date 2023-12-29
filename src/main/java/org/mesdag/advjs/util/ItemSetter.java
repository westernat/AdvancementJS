package org.mesdag.advjs.util;

import com.google.common.collect.ImmutableSet;
import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Set;

public interface ItemSetter {
    default ItemPredicate warpItem(Ingredient ingredient) {
        ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
        for (ItemStack itemStack : ingredient.getItems()) {
            builder.add(itemStack.getItem());
        }
        return new ItemPredicate(
            null,
            builder.build(),
            MinMaxBounds.Ints.exactly(1),
            MinMaxBounds.Ints.ANY,
            EnchantmentPredicate.NONE,
            EnchantmentPredicate.NONE,
            null,
            NbtPredicate.ANY
        );
    }
}
