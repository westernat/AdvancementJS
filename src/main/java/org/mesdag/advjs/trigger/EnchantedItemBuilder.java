package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class EnchantedItemBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item;
    MinMaxBounds.Ints levels;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    public void setLevels(MinMaxBounds.Ints levels) {
        this.levels = levels;
    }
}
