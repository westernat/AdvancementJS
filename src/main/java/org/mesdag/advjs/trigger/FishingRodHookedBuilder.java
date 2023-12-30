package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    EntityPredicate.Composite entity = EntityPredicate.Composite.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    public void setRod(Ingredient rod){
        this.item = warpItem(rod);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Composite.wrap(entity);
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }
}
