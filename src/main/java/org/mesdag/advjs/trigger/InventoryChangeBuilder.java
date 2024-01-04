package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.util.ItemSetter;

import java.util.stream.Stream;

class InventoryChangeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    MinMaxBounds.Ints slotsOccupied = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints slotsFull = MinMaxBounds.Ints.ANY;
    MinMaxBounds.Ints slotsEmpty = MinMaxBounds.Ints.ANY;
    ItemPredicate[] items = new ItemPredicate[]{ItemPredicate.ANY};

    @Info("The amount of slots occupied in the inventory.")
    public void setSlotsOccupied(MinMaxBounds.Ints slotsOccupied) {
        this.slotsOccupied = slotsOccupied;
    }

    @Info("The amount of slots completely filled (stacksize) in the inventory.")
    public void setSlotsFull(MinMaxBounds.Ints slotsFull) {
        this.slotsFull = slotsFull;
    }

    @Info("The amount of slots empty in the inventory.")
    public void setSlotsEmpty(MinMaxBounds.Ints slotsEmpty) {
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
