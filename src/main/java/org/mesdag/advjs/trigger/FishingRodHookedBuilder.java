package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    public void setRod(IngredientJS ingredientJS) {
        this.rod = warpItem(ingredientJS);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
