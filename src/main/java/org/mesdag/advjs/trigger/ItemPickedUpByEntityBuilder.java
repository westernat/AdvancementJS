package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

class ItemPickedUpByEntityBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    EntityPredicate.Extended entity = EntityPredicate.Extended.EMPTY;

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }

    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.Extended.ofLegacy(entity);
    }

    public void setEntity(Identifier entityId) {
        this.entity = warpEntity(entityId);
    }

    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
