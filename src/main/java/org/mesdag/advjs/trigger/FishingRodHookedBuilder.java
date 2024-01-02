package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class FishingRodHookedBuilder extends AbstractTriggerBuilder implements ItemSetter {
    ItemPredicate rod = ItemPredicate.ANY;
    EntityPredicate.Composite entity = EntityPredicate.Composite.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The fishing rod used.")
    public void setRod(ItemPredicate rod) {
        this.rod = rod;
    }

    @Info("The fishing rod used.")
    public void setRod(Ingredient rod){
        this.item = warpItem(rod);
    }

    @Info("The entity that was pulled, or the fishing bobber if no entity is pulled.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Composite.wrap(entity);
    }

    @Info("The item that was caught.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item that was caught.")
    public void setItem(Ingredient ingredient){
        this.item = warpItem(ingredient);
    }
}
