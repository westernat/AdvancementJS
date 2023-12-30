package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

class PickedUpItemBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    EntityPredicate.Composite entity = EntityPredicate.Composite.ANY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Composite.wrap(entity);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
