package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.EntitySetter;
import org.mesdag.advjs.util.ItemSetter;

class PlayerInteractBuilder extends AbstractTriggerBuilder implements ItemSetter, EntitySetter {
    ItemPredicate item = ItemPredicate.ANY;
    LootContextPredicate entity = LootContextPredicate.EMPTY;

    @Info("The item which was in the player's hand during interaction.")
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("The item which was in the player's hand during interaction.")
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }

    @Info("The entity which was interacted with.")
    public void setEntity(EntityPredicate entity) {
        this.entity = EntityPredicate.asLootContextPredicate(entity);
    }

    @Info("The entity which was interacted with.")
    public void setEntity(EntityType<?> entityType) {
        this.entity = warpEntity(entityType);
    }
}
