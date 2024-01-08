package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;

class EntityPredicateBuilder {
    final EntityPredicate.Builder builder = new EntityPredicate.Builder();

    public void of(EntityType<?> entityType) {
        builder.of(entityType);
    }

    @Info("Test this entity's type.")
    public void type(EntityTypePredicate entityTypePredicate) {
        builder.entityType(entityTypePredicate);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
        
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void distance(DistancePredicate distancePredicate) {
        builder.distance(distancePredicate);
    }

    @Info("Test properties of this entity's location.")
    public void located(LocationPredicate locationPredicate) {
        builder.located(locationPredicate);
    }

    @Info("Test properties of the block the entity is standing on, using a location predicate.")
    public void steppingOn(LocationPredicate locationPredicate) {
        builder.steppingOn(locationPredicate);
    }

    @Info("For testing the active status effects on the entity.")
    public void effects(MobEffectsPredicate mobEffectsPredicate) {
        builder.effects(mobEffectsPredicate);
    }

    @Info("Test NBT data of this entity.")
    public void nbt(CompoundTag nbt) {
        builder.nbt(new NbtPredicate(nbt));
    }

    @Info("To test flags of the entity.")
    public void flags(EntityFlagsPredicate entityFlagsPredicate) {
        builder.flags(entityFlagsPredicate);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public void equipment(EntityEquipmentPredicate entityEquipmentPredicate) {
        builder.equipment(entityEquipmentPredicate);
    }

    // TODO EntitySubPredicate

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void vehicle(EntityPredicate entityPredicate) {
        builder.vehicle(entityPredicate);
    }

    @Info("Test the entity directly riding this entity.")
    public void passenger(EntityPredicate entityPredicate) {
        builder.passenger(entityPredicate);
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void targetedEntity(EntityPredicate entityPredicate) {
        builder.targetedEntity(entityPredicate);
    }

    @Info("Passes if the team of this entity matches this string.")
    public void team(String team) {
        builder.team(team);
    }

    EntityPredicate build() {
        return builder.build();
    }
}
