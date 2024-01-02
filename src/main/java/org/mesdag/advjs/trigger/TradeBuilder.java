package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

class TradeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    EntityPredicate.Composite villager = EntityPredicate.Composite.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The villager the item was purchased from.")
    public void setVillager(EntityPredicate villager) {
        this.villager = EntityPredicate.Composite.wrap(villager);
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
