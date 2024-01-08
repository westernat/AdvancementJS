package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class EnchantedItemBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate item;
    NumberRange.IntRange levels;

    @Info("The item after it has been enchanted.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item after it has been enchanted.")
    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }

    @Info("The levels spent by the player on the enchantment.")
    public void setLevels(NumberRange.IntRange levels) {
        this.levels = levels;
    }
}
