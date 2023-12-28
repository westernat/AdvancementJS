package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
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

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
