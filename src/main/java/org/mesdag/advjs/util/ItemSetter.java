package org.mesdag.advjs.util;

import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.NbtPredicate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Arrays;
import java.util.stream.Collectors;

@HideFromJS
public interface ItemSetter {
    default ItemPredicate wrapItem(Ingredient ingredient) {
        return new ItemPredicate(
            null,
            Arrays.stream(ingredient.getItems()).map(ItemStack::getItem).collect(Collectors.toSet()),
            MinMaxBounds.Ints.ANY,
            MinMaxBounds.Ints.ANY,
            EnchantmentPredicate.NONE,
            EnchantmentPredicate.NONE,
            null,
            NbtPredicate.ANY
        );
    }
}
