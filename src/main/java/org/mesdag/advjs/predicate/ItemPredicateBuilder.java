package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import org.mesdag.advjs.util.Bounds;

import java.util.Arrays;
import java.util.function.Consumer;

public class ItemPredicateBuilder {
    final ItemPredicate.Builder builder = ItemPredicate.Builder.item();

    @Info("Check items.")
    public void of(Ingredient ingredient) {
        builder.of(Arrays.stream(ingredient.getItems()).map(ItemStack::getItem).toArray(ItemLike[]::new));
    }

    @Info("Check tag.")
    public void tag(ResourceLocation tag) {
        builder.of(TagKey.create(Registries.ITEM, tag));
    }

    @Info("""
        Test the number of items in this item stack.
                
        Use an integer to test for a single value.
        """)
    public void withCount(Bounds bounds) {
        builder.withCount(bounds.toIntegerBounds());
    }

    @Info("""
        Test the durability of the items in this stack,
                
        represented by the number of uses remaining (not number of uses consumed).
        """)
    public void hasDurability(Bounds bounds) {
        builder.hasDurability(bounds.toIntegerBounds());
    }

    @Info("Test the type of potion this item is. Accepts a brewed potion ID.")
    public void isPotion(ResourceLocation location) {
        builder.isPotion(BuiltInRegistries.POTION.get(location));
    }

    @Info("Test for any other NBT tags that may be present on the item.")
    public void hasNbt(CompoundTag nbt) {
        builder.hasNbt(nbt);
    }

    @Info("Test the enchantment.")
    public void hasEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.hasEnchantment(enchantmentPredicate);
    }

    @Info("Test the enchantment.")
    public void hasEnchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder1 = new EnchantmentPredicateBuilder();
        consumer.accept(builder1);
        builder.hasEnchantment(builder1.build());
    }

    @Info("To test for stored enchantments on an enchanted book.")
    public void hasStoredEnchantment(EnchantmentPredicate enchantmentPredicate) {
        builder.hasStoredEnchantment(enchantmentPredicate);
    }

    @Info("To test for stored enchantments on an enchanted book.")
    public void hasStoredEnchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder1 = new EnchantmentPredicateBuilder();
        consumer.accept(builder1);
        builder.hasStoredEnchantment(builder1.build());
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
