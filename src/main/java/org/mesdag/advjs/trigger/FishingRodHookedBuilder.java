package org.mesdag.advjs.trigger;

import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    public void setRod(Ingredient ingredient) {
        this.rod = warpItem(ingredient);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
