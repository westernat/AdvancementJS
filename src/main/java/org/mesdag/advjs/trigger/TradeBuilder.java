package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class TradeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    EntityPredicate.Composite villager = EntityPredicate.Composite.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Composite.wrap(villager);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }
}
