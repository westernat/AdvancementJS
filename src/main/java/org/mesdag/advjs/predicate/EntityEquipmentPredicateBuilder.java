package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;

class EntityEquipmentPredicateBuilder {
    final EntityEquipmentPredicate.Builder builder = new EntityEquipmentPredicate.Builder();

    @Info("Test the item in the entity's head armor slot.")
    public void head(ItemPredicate itemPredicate) {
        builder.head(itemPredicate);
    }

    @Info("Test the item in the entity's chest armor slot.")
    public void chest(ItemPredicate itemPredicate) {
        builder.chest(itemPredicate);
    }

    @Info("Test the item in the entity's legs armor slot.")
    public void legs(ItemPredicate itemPredicate) {
        builder.legs(itemPredicate);
    }

    @Info("Test the item in the entity's feet armor slot.")
    public void feet(ItemPredicate itemPredicate) {
        builder.feet(itemPredicate);
    }

    @Info("Test the item in the entity's main hand.")
    public void mainhand(ItemPredicate itemPredicate) {
        builder.mainhand(itemPredicate);
    }

    @Info("Test the item in the entity's offhand.")
    public void offhand(ItemPredicate itemPredicate) {
        builder.offhand(itemPredicate);
    }

    EntityEquipmentPredicate build() {
        return builder.build();
    }
}
