package org.mesdag.advjs.predicate;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;

public class Predicate {
    public BlockPredicate blockPredicate(JsonObject o) {
        if (o == null) return BlockPredicate.ANY;
        return BlockPredicate.fromJson(o);
    }

    public BlockPredicateBuilder blockPredicateBuilder() {
        return new BlockPredicateBuilder();
    }

    public DamagePredicate damagePredicate(JsonObject o) {
        if (o == null) return DamagePredicate.ANY;
        return DamagePredicate.fromJson(o);
    }

    public DamagePredicate.Builder damagePredicateBuilder() {
        return new DamagePredicate.Builder();
    }

    public DamageSourcePredicate damageSourcePredicate(JsonObject o) {
        if (o == null) return DamageSourcePredicate.ANY;
        return DamageSourcePredicate.fromJson(o);
    }

    public DamageSourcePredicate.Builder damageSourcePredicateBuilder() {
        return DamageSourcePredicate.Builder.damageType();
    }

    public DistancePredicate distancePredicate(JsonObject o) {
        if (o == null) return DistancePredicate.ANY;
        return DistancePredicate.fromJson(o);
    }

    public DistancePredicate horizontalDistance(MinMaxBounds.Doubles bounds) {
        return new DistancePredicate(MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, bounds, MinMaxBounds.Doubles.ANY);
    }

    public DistancePredicate verticalDistance(MinMaxBounds.Doubles bounds) {
        return new DistancePredicate(MinMaxBounds.Doubles.ANY, bounds, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY);
    }

    public DistancePredicate absoluteDistance(MinMaxBounds.Doubles bounds) {
        return new DistancePredicate(MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, MinMaxBounds.Doubles.ANY, bounds);
    }

    public EnchantmentPredicate enchantmentPredicate(JsonObject o) {
        if (o == null) return EnchantmentPredicate.ANY;
        return EnchantmentPredicate.fromJson(o);
    }

    public EnchantmentPredicateBuilder enchantmentPredicateBuilder() {
        return new EnchantmentPredicateBuilder();
    }

    public EnchantmentPredicate[] enchantmentPredicateArray(JsonArray o) {
        if (o == null) return EnchantmentPredicate.NONE;
        return EnchantmentPredicate.fromJsonArray(o);
    }

    public EntityEquipmentPredicate entityEquipmentPredicate(JsonObject o) {
        if (o == null) return EntityEquipmentPredicate.ANY;
        return EntityEquipmentPredicate.fromJson(o);
    }

    public EntityEquipmentPredicate.Builder entityEquipmentPredicateBuilder() {
        return EntityEquipmentPredicate.Builder.equipment();
    }

    public EntityFlagsPredicate entityFlagsPredicate(JsonObject o) {
        if (o == null) return EntityFlagsPredicate.ANY;
        return EntityFlagsPredicate.fromJson(o);
    }

    public EntityFlagsPredicate.Builder entityFlagsPredicateBuilder() {
        return EntityFlagsPredicate.Builder.flags();
    }

    public EntityPredicate entityPredicate(JsonObject o) {
        if (o == null) return EntityPredicate.ANY;
        return EntityPredicate.fromJson(o);
    }

    //TODO entityPredicateBuilder

    public FishingHookPredicate inOpenWater(boolean bool) {
        return FishingHookPredicate.inOpenWater(bool);
    }

    public FluidPredicate fluidPredicate(JsonObject o) {
        if (o == null) return FluidPredicate.ANY;
        return FluidPredicate.fromJson(o);
    }

    //TODO fluidPredicateBuilder

    public ItemPredicate itemPredicate(JsonObject o) {
        if (o == null) return ItemPredicate.ANY;
        return ItemPredicate.fromJson(o);
    }

    //TODO itemPredicateBuilder

    public LighthingBoltPredicate lightningBoltPredicate(JsonObject o) {
        if (o == null) return LighthingBoltPredicate.ANY;
        return LighthingBoltPredicate.fromJson(o);
    }

    //TODO lightningBoltPredicateBuilder

    public LightPredicate lightPredicate(JsonObject o) {
        if (o == null) return LightPredicate.ANY;
        return LightPredicate.fromJson(o);
    }

    public LightPredicate.Builder lightPredicate() {
        return new LightPredicate.Builder();
    }

    public LocationPredicate locationPredicate(JsonObject o) {
        if (o == null) return LocationPredicate.ANY;
        return LocationPredicate.fromJson(o);
    }

    //TODO locationPredicateBuilder

    public MobEffectsPredicate mobEffectsPredicate(JsonObject o) {
        if (o == null) return MobEffectsPredicate.ANY;
        return MobEffectsPredicate.fromJson(o);
    }

    public MobEffectsPredicate mobEffectsPredicateBuilder() {
        return new MobEffectsPredicate(Maps.newLinkedHashMap());
    }

    public MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstancePredicate(JsonObject o) {
        if (o == null) return new MobEffectsPredicate.MobEffectInstancePredicate();
        return MobEffectsPredicate.MobEffectInstancePredicate.fromJson(o);
    }

    public MobEffectInstancePredicateBuilder mobEffectInstancePredicateBuilder() {
        return new MobEffectInstancePredicateBuilder();
    }

    public NbtPredicate nbtPredicate(CompoundTag nbt) {
        if (nbt == null) return NbtPredicate.ANY;
        return new NbtPredicate(nbt);
    }

    public PlayerPredicate playerPredicate(JsonObject o) {
        if (o == null) return PlayerPredicate.ANY;
        return PlayerPredicate.fromJson(o);
    }

    //TODO playerPredicateBuilder

    public StatePropertiesPredicate statePropertiesPredicate(JsonObject o) {
        if (o == null) return StatePropertiesPredicate.ANY;
        return StatePropertiesPredicate.fromJson(o);
    }

    public StatePropertiesPredicateBuilder statePropertiesPBuilder() {
        return new StatePropertiesPredicateBuilder();
    }
}
