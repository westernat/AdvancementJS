package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.function.Consumer;

class RecipeCraftedBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    ResourceLocation recipe = new ResourceLocation("wooden_sword");
    final ArrayList<ItemPredicate> predicates = new ArrayList<>();

    @Info("The recipe that was crafted.")
    public void setRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
    }

    @Info("""
        Add an item predicates for the recipe ingredients.
                
        Each item can only match one predicate, and every predicate needs to pass for the criterion to be granted.
        """)
    public void addItem(ItemPredicate itemPredicate) {
        this.predicates.add(itemPredicate);
    }

    @Info("""
        Add an item predicates for the recipe ingredients.
                
        Each item can only match one predicate, and every predicate needs to pass for the criterion to be granted.
        """)
    public void addItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.predicates.add(builder.build());
    }

    @Info("""
        Add an item predicates for the recipe ingredients.
                
        Each item can only match one predicate, and every predicate needs to pass for the criterion to be granted.
        """)
    public void addItem(Ingredient ingredient) {
        this.predicates.add(wrapItem(ingredient));
    }
}
