package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

import java.util.stream.Stream;

class ItemPredicateBuilder {
    final ItemPredicate.Builder builder = ItemPredicate.Builder.item();

    @Info("Check items.")
    public void of(ItemStack... itemStacks) {
        builder.of(Stream.of(itemStacks).map(ItemStack::getItem).toArray(ItemLike[]::new));
    }

    @Info("""
        Test the number of items in this item stack.
                
        Use an integer to test for a single value.
        """)
    public void withCount(MinMaxBounds.Ints bounds) {
        builder.withCount(bounds);
    }

    @Info("""
        Test the durability of the items in this stack,
                
        represented by the number of uses remaining (not number of uses consumed).
        """)
    public void hasDurability(MinMaxBounds.Ints bounds) {
        builder.hasDurability(bounds);
    }

    @Info("Test the type of potion this item is. Accepts a brewed potion ID.")
    public void isPotion(ResourceLocation location) {
        builder.isPotion(Registry.POTION.get(location));
    }

    @Info("Test for any other NBT tags that may be present on the item.")
    public void hasNbt(CompoundTag nbt) {
        builder.hasNbt(nbt);
    }

    @Info("Test the enchantment.")
    public void hasEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.hasEnchantment(enchantmentPredicate);
    }

    @Info("To test for stored enchantments on an enchanted book.")
    public void hasStoredEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.hasStoredEnchantment(enchantmentPredicate);
    }

    ItemPredicate build() {
        return builder.build();
    }
}
