package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

public class ItemPickedUpByPlayerBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    ContextAwarePredicate entity = ContextAwarePredicate.ANY;

    @Info("The item thrown.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item thrown.")
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }

    @Info("The entity that threw the item.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.wrap(entity);
    }

    @Info("The entity that threw the item.")
    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
