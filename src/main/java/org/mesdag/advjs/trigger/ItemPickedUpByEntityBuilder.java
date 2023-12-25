package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

class ItemPickedUpByEntityBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    EntityPredicate.Composite entity = EntityPredicate.Composite.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Composite.wrap(entity);
    }

    public void setEntity(ResourceLocation entityId) {
        this.entity = warpEntity(entityId);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
