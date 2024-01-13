package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.function.Consumer;

public class EntityEquipmentPredicateBuilder {
    final EntityEquipmentPredicate.Builder builder = new EntityEquipmentPredicate.Builder();

    @Info("Test the item in the entity's head armor slot.")
    public void head(ItemPredicate itemPredicate) {
        builder.head(itemPredicate);
    }

    @Info("Test the item in the entity's head armor slot.")
    public void head(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.head(builder1.build());
    }

    @Info("Test the item in the entity's chest armor slot.")
    public void chest(ItemPredicate itemPredicate) {
        builder.chest(itemPredicate);
    }

    @Info("Test the item in the entity's chest armor slot.")
    public void chest(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.chest(builder1.build());
    }

    @Info("Test the item in the entity's legs armor slot.")
    public void legs(ItemPredicate itemPredicate) {
        builder.legs(itemPredicate);
    }

    @Info("Test the item in the entity's legs armor slot.")
    public void legs(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.legs(builder1.build());
    }

    @Info("Test the item in the entity's feet armor slot.")
    public void feet(ItemPredicate itemPredicate) {
        builder.feet(itemPredicate);
    }

    @Info("Test the item in the entity's feet armor slot.")
    public void feet(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.feet(builder1.build());
    }

    @Info("Test the item in the entity's main hand.")
    public void mainhand(ItemPredicate itemPredicate) {
        builder.mainhand(itemPredicate);
    }

    @Info("Test the item in the entity's main hand.")
    public void mainhand(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.mainhand(builder1.build());
    }

    @Info("Test the item in the entity's offhand.")
    public void offhand(ItemPredicate itemPredicate) {
        builder.offhand(itemPredicate);
    }

    @Info("Test the item in the entity's offhand.")
    public void offhand(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.offhand(builder1.build());
    }

    EntityEquipmentPredicate build() {
        return builder.build();
    }
}
