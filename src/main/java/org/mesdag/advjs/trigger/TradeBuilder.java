package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class TradeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    LootContextPredicate villager = LootContextPredicate.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The villager the item was purchased from.")
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.asLootContextPredicate(villager);
    }

    @Info("""
        The item that was purchased.
                
        The 'count' tag checks the count from one trade, not multiple.
        """)
    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    @Info("""
        The item that was purchased.
                
        The 'count' tag checks the count from one trade, not multiple.
        """)
    public void setItem(Ingredient ingredient) {
        this.item = warpItem(ingredient);
    }
}
