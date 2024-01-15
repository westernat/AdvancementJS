package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.*;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class EntityPredicateBuilder {
    final EntityPredicate.Builder builder = new EntityPredicate.Builder();

    @Info("Accept entity's id.")
    public void of(EntityType<?> entityType) {
        builder.type(entityType);
    }

    @Info("Accept entity's tag.")
    public void tag(Identifier tag) {
        builder.type(TagKey.of(RegistryKeys.ENTITY_TYPE, tag));
    }

    @Info("Test this entity's type.")
    public void type(EntityTypePredicate entityTypePredicate) {
        builder.type(entityTypePredicate);
    }

    @Info("Test this entity's type.")
    public void type(Consumer<EntityTypePredicateBuilder> consumer) {
        EntityTypePredicateBuilder builder1 = new EntityTypePredicateBuilder();
        consumer.accept(builder1);
        builder.type(builder1.predicate);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
        
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void distance(DistancePredicate distancePredicate) {
        builder.distance(distancePredicate);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder1 = new DistancePredicateBuilder();
        consumer.accept(builder1);
        builder.distance(builder1.build());
    }

    @Info("Test properties of this entity's location.")
    public void located(LocationPredicate locationPredicate) {
        builder.location(locationPredicate);
    }

    @Info("Test properties of this entity's location.")
    public void located(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        builder.location(builder1.build());
    }

    @Info("Test properties of the block the entity is standing on, using a location predicate.")
    public void steppingOn(LocationPredicate locationPredicate) {
        builder.steppingOn(locationPredicate);
    }

    @Info("Test properties of the block the entity is standing on, using a location predicate.")
    public void steppingOn(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        builder.steppingOn(builder1.build());
    }

    @Info("For testing the active status effects on the entity.")
    public void effects(EntityEffectPredicate mobEffectsPredicate) {
        builder.effects(mobEffectsPredicate);
    }

    @Info("For testing the active status effects on the entity.")
    public void effects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder1 = new MobEffectsPredicateBuilder();
        consumer.accept(builder1);
        builder.effects(builder1.build());
    }

    @Info("Test NBT data of this entity.")
    public void nbt(NbtCompound nbt) {
        builder.nbt(new NbtPredicate(nbt));
    }

    @Info("To test flags of the entity.")
    public void flags(EntityFlagsPredicate entityFlagsPredicate) {
        builder.flags(entityFlagsPredicate);
    }

    @Info("To test flags of the entity.")
    public void flags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder1 = new EntityFlagsPredicateBuilder();
        consumer.accept(builder1);
        builder.flags(builder1.build());
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public void equipment(EntityEquipmentPredicate entityEquipmentPredicate) {
        builder.equipment(entityEquipmentPredicate);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public void equipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder1 = new EntityEquipmentPredicateBuilder();
        consumer.accept(builder1);
        builder.equipment(builder1.build());
    }

    // TODO EntitySubPredicate

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void vehicle(EntityPredicate entityPredicate) {
        builder.vehicle(entityPredicate);
    }

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void vehicle(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.vehicle(builder1.build());
    }

    @Info("Test the entity directly riding this entity.")
    public void passenger(EntityPredicate entityPredicate) {
        builder.passenger(entityPredicate);
    }

    @Info("Test the entity directly riding this entity.")
    public void passenger(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.passenger(builder1.build());
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void targetedEntity(EntityPredicate entityPredicate) {
        builder.targetedEntity(entityPredicate);
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void targetedEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.targetedEntity(builder1.build());
    }

    @Info("Passes if the team of this entity matches this string.")
    public void team(String team) {
        builder.team(team);
    }

    @HideFromJS
    public EntityPredicate build() {
        return builder.build();
    }
}
