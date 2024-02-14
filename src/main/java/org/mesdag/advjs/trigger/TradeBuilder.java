package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.ItemPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.util.ItemSetter;

import java.util.function.Consumer;

public class TradeBuilder extends BaseTriggerInstanceBuilder implements ItemSetter {
    LootContextPredicate villager = LootContextPredicate.EMPTY;
    ItemPredicate item = ItemPredicate.ANY;

    @Info("The villager the item was purchased from.")
    public void setVillagerByPredicate(EntityPredicate villager) {
        this.villager = wrapEntity(villager);
    }

    @Info("The villager the item was purchased from.")
    public void setVillager(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        this.villager = wrapEntity(builder.build());
    }

    @Info("The villager the item was purchased from.")
    public void setVillagerByCondition(ICondition... conditions) {
        this.villager = wrapEntity(conditions);
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
    public void setItem(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        this.item = builder.build();
    }

    @Info("""
        The item that was purchased.
                
        The 'count' tag checks the count from one trade, not multiple.
        """)
    public void setItem(Ingredient ingredient) {
        this.item = wrapItem(ingredient);
    }
}
