package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.function.Consumer;

public class EntityEquipmentPredicateBuilder {
    final EntityEquipmentPredicate.Builder builder = new EntityEquipmentPredicate.Builder();

    @Info("Test the item in the entity's head armor slot.")
    public void setHeadByPredicate(ItemPredicate itemPredicate) {
        builder.head(itemPredicate);
    }

    @Info("Test the item in the entity's head armor slot.")
    public void setHead(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.head(builder1.build());
    }

    @Info("Test the item in the entity's chest armor slot.")
    public void setChestByPredicate(ItemPredicate itemPredicate) {
        builder.chest(itemPredicate);
    }

    @Info("Test the item in the entity's chest armor slot.")
    public void setChest(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.chest(builder1.build());
    }

    @Info("Test the item in the entity's legs armor slot.")
    public void setLegsByPredicate(ItemPredicate itemPredicate) {
        builder.legs(itemPredicate);
    }

    @Info("Test the item in the entity's legs armor slot.")
    public void setLegs(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.legs(builder1.build());
    }

    @Info("Test the item in the entity's feet armor slot.")
    public void setFeetByPredicate(ItemPredicate itemPredicate) {
        builder.feet(itemPredicate);
    }

    @Info("Test the item in the entity's feet armor slot.")
    public void setFeet(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.feet(builder1.build());
    }

    @Info("Test the item in the entity's main hand.")
    public void setMainhandByPredicate(ItemPredicate itemPredicate) {
        builder.mainhand(itemPredicate);
    }

    @Info("Test the item in the entity's main hand.")
    public void setMainhand(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.mainhand(builder1.build());
    }

    @Info("Test the item in the entity's offhand.")
    public void setOffhandByPredicate(ItemPredicate itemPredicate) {
        builder.offhand(itemPredicate);
    }

    @Info("Test the item in the entity's offhand.")
    public void setOffhand(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder1 = new ItemPredicateBuilder();
        consumer.accept(builder1);
        builder.offhand(builder1.build());
    }

    EntityEquipmentPredicate build() {
        return builder.build();
    }
}
