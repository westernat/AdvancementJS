package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

public class ItemPickedUpByPlayerBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;

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
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    @Info("The entity that threw the item.")
    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}