package org.mesdag.advjs.util;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;

import java.util.Arrays;
import java.util.stream.Collectors;

@HideFromJS
public interface ItemSetter {
    default ItemPredicate wrapItem(Ingredient ingredient) {
        return new ItemPredicate(
            null,
            Arrays.stream(ingredient.getMatchingStacks()).map(ItemStack::getItem).collect(Collectors.toSet()),
            NumberRange.IntRange.ANY,
            NumberRange.IntRange.ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            null,
            NbtPredicate.ANY
        );
    }
}
