package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.function.Consumer;

public class ItemPredicateBuilder {
    final ItemPredicate.Builder builder = ItemPredicate.Builder.create();

    @Info("Check items.")
    public void of(Ingredient ingredient) {
        builder.items(Arrays.stream(ingredient.getMatchingStacks()).map(ItemStack::getItem).toArray(ItemConvertible[]::new));
    }

    @Info("Check tag.")
    public void tag(Identifier tag) {
        builder.tag(TagKey.of(RegistryKeys.ITEM, tag));
    }

    @Info("""
        Test the number of items in this item stack.
                
        Use an integer to test for a single value.
        """)
    public void withCount(NumberRange.IntRange bounds) {
        builder.count(bounds);
    }

    @Info("""
        Test the durability of the items in this stack,
                
        represented by the number of uses remaining (not number of uses consumed).
        """)
    public void hasDurability(NumberRange.IntRange bounds) {
        builder.durability(bounds);
    }

    @Info("Test the type of potion this item is. Accepts a brewed potion ID.")
    public void isPotion(Identifier location) {
        builder.potion(Registries.POTION.get(location));
    }

    @Info("Test for any other NBT tags that may be present on the item.")
    public void hasNbt(NbtCompound nbt) {
        builder.nbt(nbt);
    }

    @Info("Test the enchantment.")
    public void hasEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.enchantment(enchantmentPredicate);
    }

    @Info("Test the enchantment.")
    public void hasEnchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder1 = new EnchantmentPredicateBuilder();
        consumer.accept(builder1);
        builder.enchantment(builder1.build());
    }

    @Info("To test for stored enchantments on an enchanted book.")
    public void hasStoredEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.storedEnchantment(enchantmentPredicate);
    }

    @Info("To test for stored enchantments on an enchanted book.")
    public void hasStoredEnchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder1 = new EnchantmentPredicateBuilder();
        consumer.accept(builder1);
        builder.storedEnchantment(builder1.build());
    }

    @HideFromJS
    public ItemPredicate build() {
        return builder.build();
    }

    @HideFromJS
    public ItemPredicate.Builder getBuilder() {
        return builder;
    }
}
