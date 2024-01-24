package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class Predicate {
    public BlockPredicate blockFromJson(JsonObject o) {
        return BlockPredicate.fromJson(o);
    }

    public BlockPredicate block(Consumer<BlockPredicateBuilder> consumer) {
        BlockPredicateBuilder builder = new BlockPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public BlockPredicate anyBlock() {
        return BlockPredicate.ANY;
    }

    public DamagePredicate damageFromJson(JsonObject o) {
        return DamagePredicate.fromJson(o);
    }

    public DamagePredicate damage(Consumer<DamagePredicateBuilder> consumer) {
        DamagePredicateBuilder builder = new DamagePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public DamagePredicate anyDamage() {
        return DamagePredicate.ANY;
    }

    public DamageSourcePredicate damageSource(JsonObject o) {
        return DamageSourcePredicate.fromJson(o);
    }

    public DamageSourcePredicate damageSource(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public DamageSourcePredicate anyDamageSource() {
        return DamageSourcePredicate.EMPTY;
    }

    public DistancePredicate distanceFromJson(JsonObject o) {
        return DistancePredicate.fromJson(o);
    }

    public DistancePredicate distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public DistancePredicate anyDistance() {
        return DistancePredicate.ANY;
    }

    public EnchantmentPredicate enchantmentFromJson(JsonObject o) {
        return EnchantmentPredicate.deserialize(o);
    }

    public EnchantmentPredicate enchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder = new EnchantmentPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EnchantmentPredicate anyEnchantment() {
        return EnchantmentPredicate.ANY;
    }

    public EnchantmentPredicate[] enchantmentArray(JsonArray o) {
        return EnchantmentPredicate.deserializeAll(o);
    }

    public EntityEquipmentPredicate entityEquipmentFromJson(JsonObject o) {
        return EntityEquipmentPredicate.fromJson(o);
    }

    public EntityEquipmentPredicate entityEquipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder =new EntityEquipmentPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityEquipmentPredicate anyEntityEquipment() {
        return EntityEquipmentPredicate.ANY;
    }

    public EntityFlagsPredicate entityFlagsFromJson(JsonObject o) {
        return EntityFlagsPredicate.fromJson(o);
    }

    public EntityFlagsPredicate entityFlags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder =new EntityFlagsPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityFlagsPredicate anyEntityFlags() {
        return EntityFlagsPredicate.ANY;
    }

    public EntityPredicate entityFromJson(JsonObject o) {
        return EntityPredicate.fromJson(o);
    }

    public EntityPredicate entity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityPredicate anyEntity() {
        return EntityPredicate.ANY;
    }

    public FluidPredicate fluidFromJson(JsonObject o) {
        return FluidPredicate.fromJson(o);
    }

    public FluidPredicate fluid(Consumer<FluidPredicateBuilder> consumer) {
        FluidPredicateBuilder builder =new FluidPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public FluidPredicate anyFluid() {
        return FluidPredicate.ANY;
    }

    public ItemPredicate itemFromJson(JsonObject o) {
        return ItemPredicate.fromJson(o);
    }

    public ItemPredicate item(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder =new ItemPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public ItemPredicate anyItem() {
        return ItemPredicate.ANY;
    }

    public LightPredicate lightFromJson(JsonObject o) {
        return LightPredicate.fromJson(o);
    }

    public LightPredicate light(Bounds bounds) {
        return new LightPredicate.Builder().light(bounds.toIntegerBounds()).build();
    }

    public LightPredicate anyLight() {
        return LightPredicate.ANY;
    }

    public LocationPredicate locationFromJson(JsonObject o) {
        return LocationPredicate.fromJson(o);
    }

    public LocationPredicate location(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder =new LocationPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public LocationPredicate anyLocation() {
        return LocationPredicate.ANY;
    }

    public EntityEffectPredicate mobEffectsFromJson(JsonObject o) {
        return EntityEffectPredicate.fromJson(o);
    }

    public EntityEffectPredicate mobEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder = new MobEffectsPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityEffectPredicate anyMobEffects() {
        return EntityEffectPredicate.EMPTY;
    }

    public EntityEffectPredicate.EffectData mobEffectInstanceFromJson(JsonObject o) {
        return EntityEffectPredicate.EffectData.fromJson(o);
    }

    public EntityEffectPredicate.EffectData mobEffectInstance(Consumer<MobEffectInstancePredicateBuilder> consumer) {
        MobEffectInstancePredicateBuilder builder = new MobEffectInstancePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityEffectPredicate.EffectData anyMobEffectInstance() {
        return new EntityEffectPredicate.EffectData();
    }

    public NbtPredicate nbt(NbtCompound nbt) {
        return new NbtPredicate(nbt);
    }

    public NbtPredicate anyNbt() {
        return NbtPredicate.ANY;
    }

    public PlayerPredicate player(JsonObject o) {
        return PlayerPredicate.fromJson(o);
    }

    public StatePredicate statePropertiesFromJson(JsonObject o) {
        return StatePredicate.fromJson(o);
    }

    public StatePredicate stateProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder = new StatePropertiesPredicateBuilder();
        consumer.accept(builder);
        return builder.builder.build();
    }

    public StatePredicate anyStateProperties() {
        return StatePredicate.ANY;
    }

    public EntityTypePredicate entityTypeFromJson(JsonObject o) {
        return EntityTypePredicate.fromJson(o);
    }

    public EntityTypePredicate entityType(Consumer<EntityTypePredicateBuilder> consumer) {
        EntityTypePredicateBuilder builder = new EntityTypePredicateBuilder();
        consumer.accept(builder);
        return builder.predicate;
    }

    public EntityTypePredicate anyEntityType() {
        return EntityTypePredicate.ANY;
    }
}
