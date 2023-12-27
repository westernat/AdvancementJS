package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.item.ingredient.IngredientJS;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.mesdag.advjs.util.BlockSetter;
import org.mesdag.advjs.util.ItemSetter;

import javax.annotation.Nullable;

class PlacedBlockBuilder extends AbstractTriggerBuilder implements BlockSetter, ItemSetter {
    @Nullable
    Block block = null;
    StatePropertiesPredicate state = StatePropertiesPredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    ItemPredicate item = ItemPredicate.ANY;

    public void setBlock(ResourceLocation blockId) {
        this.block = warpBlock(blockId);
    }

    public void setState(StatePropertiesPredicate state) {
        this.state = state;
    }

    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    public void setItem(ItemPredicate item) {
        this.item = item;
    }

    public void setItem(IngredientJS ingredientJS) {
        this.item = warpItem(ingredientJS);
    }
}
