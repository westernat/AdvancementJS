package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class TradeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    EntityPredicate.Extended villager = EntityPredicate.Extended.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Extended.ofLegacy(villager);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
