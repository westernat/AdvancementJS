package org.mesdag.advjs.trigger;

import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.util.ItemSetter;

import java.util.ArrayList;
import java.util.function.Consumer;

class RecipeCraftedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    Identifier recipe = new Identifier("wooden_sword");
    final ArrayList<ItemPredicate> predicates = new ArrayList<>();

    public void setRecipe(Identifier recipe) {
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
