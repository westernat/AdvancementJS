package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.ItemSetter;

import java.util.stream.Stream;

class InventoryChangeBuilder extends AbstractTriggerBuilder implements ItemSetter {
    NumberRange.IntRange slotsOccupied = NumberRange.IntRange.ANY;
    NumberRange.IntRange slotsFull = NumberRange.IntRange.ANY;
    NumberRange.IntRange slotsEmpty = NumberRange.IntRange.ANY;
    ItemPredicate[] items = new ItemPredicate[]{ItemPredicate.ANY};

    public void setSlotsOccupied(NumberRange.IntRange slotsOccupied) {
        this.slotsOccupied = slotsOccupied;
    }

    public void setSlotsFull(NumberRange.IntRange slotsFull) {
        this.slotsFull = slotsFull;
    }

    public void setSlotsEmpty(NumberRange.IntRange slotsEmpty) {
        this.slotsEmpty = slotsEmpty;
    }

    public void setItems(ItemPredicate... items) {
        this.items = items;
    }

    public void setItems(IngredientJS... ingredientJS) {
        this.items = Stream.of(ingredientJS).map(this::warpItem).toArray(ItemPredicate[]::new);
    }
}
