package org.mesdag.advjs.util;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

public interface ItemSetter {
    default ItemPredicate warpItem(IngredientJS ingredientJS) {
        return new ItemPredicate(
            null,
            ingredientJS.getVanillaItems(),
            NumberRange.IntRange.exactly(ingredientJS.getCount()),
            NumberRange.IntRange.ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            EnchantmentPredicate.ARRAY_OF_ANY,
            null,
            NbtPredicate.ANY
        );
    }
}
