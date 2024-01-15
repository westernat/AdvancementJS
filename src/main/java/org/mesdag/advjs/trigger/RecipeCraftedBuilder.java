package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.function.Consumer;

class RecipeCraftedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ResourceLocation recipe = new ResourceLocation("wooden_sword");
    final ArrayList<ItemPredicate> predicates = new ArrayList<>();

    public void setRecipe(ResourceLocation recipe) {
        this.recipe = recipe;
    }

    public void addItem(ItemPredicate itemPredicate) {
        this.predicates.add(itemPredicate);
    }

    public void addItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.predicates.add(builder.build());
    }

    public void addItem(Ingredient ingredient) {
        this.predicates.add(wrapItem(ingredient));
    }
}
