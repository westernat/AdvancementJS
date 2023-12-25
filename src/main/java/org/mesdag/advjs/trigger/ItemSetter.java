package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.NbtPredicate;

interface ItemSetter {
    default ItemPredicate warpItem(IngredientJS ingredientJS) {
        return new ItemPredicate(
            null,
            ingredientJS.getVanillaItems(),
            MinMaxBounds.Ints.exactly(ingredientJS.getCount()),
            MinMaxBounds.Ints.ANY,
            EnchantmentPredicate.NONE,
            EnchantmentPredicate.NONE,
            null,
            NbtPredicate.ANY
        );
    }
}
