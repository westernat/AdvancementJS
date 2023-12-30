package org.mesdag.advjs.trigger;

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

    public void setSlotsOccupied(MinMaxBounds.Ints slotsOccupied) {
        this.slotsOccupied = slotsOccupied;
    }

    public void setSlotsFull(MinMaxBounds.Ints slotsFull) {
        this.slotsFull = slotsFull;
    }

    public void setSlotsEmpty(MinMaxBounds.Ints slotsEmpty) {
        this.slotsEmpty = slotsEmpty;
    }

    public void setItems(ItemPredicate... items) {
        this.items = items;
    }

    public void setItems(Ingredient... ingredients) {
        this.items = Stream.of(ingredients).map(this::warpItem).toArray(ItemPredicate[]::new);
    }
}
