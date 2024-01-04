package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The fishing rod used.")
    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    @Info("The fishing rod used.")
    public void setRod(Ingredient ingredient) {
        this.rod = warpItem(ingredient);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    @Info("The item that was caught.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item that was caught.")
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
