package org.mesdag.advjs.util;

import com.google.common.collect.ImmutableSet;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;

public interface ItemSetter {
    @HideFromJS
    default ItemPredicate wrapItem(Ingredient ingredient) {
        ImmutableSet.Builder<Item> builder = ImmutableSet.builder();
        for (ItemStack itemStack : ingredient.getMatchingStacks()) {
            builder.add(itemStack.getItem());
        }
        return new ItemPredicate(
            null,
            builder.build(),
            NumberRange.IntRange.ANY,
            NumberRange.IntRange.ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            null,
            NbtPredicate.ANY
        );
    }
}
