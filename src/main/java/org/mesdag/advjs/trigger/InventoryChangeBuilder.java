package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

import java.util.stream.Stream;

class InventoryChangeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    NumberRange.IntRange slotsOccupied = NumberRange.IntRange.ANY;
    NumberRange.IntRange slotsFull = NumberRange.IntRange.ANY;
    NumberRange.IntRange slotsEmpty = NumberRange.IntRange.ANY;
    ItemPredicate[] items = new ItemPredicate[]{ItemPredicate.ANY};

    @Info("The amount of slots occupied in the inventory.")
    public void setSlotsOccupied(NumberRange.IntRange slotsOccupied) {
        this.slotsOccupied = slotsOccupied;
    }

    @Info("The amount of slots completely filled (stacksize) in the inventory.")
    public void setSlotsFull(NumberRange.IntRange slotsFull) {
        this.slotsFull = slotsFull;
    }

    @Info("The amount of slots empty in the inventory.")
    public void setSlotsEmpty(NumberRange.IntRange slotsEmpty) {
        this.slotsEmpty = slotsEmpty;
    }

    @Info("""
        A list of items in the player's inventory.
                
        All items in the list must be in the player's inventory,
                
        but not all items in the player's inventory have to be in this list.
        """)
    public void setItems(ItemPredicate... items) {
        this.items = items;
    }

    @Info("""
        A list of items in the player's inventory.
                
        All items in the list must be in the player's inventory,
                
        but not all items in the player's inventory have to be in this list.
        """)
    public void setItems(Ingredient... ingredients) {
        this.items = Stream.of(ingredients).map(this::warpItem).toArray(ItemPredicate[]::new);
    }
}
