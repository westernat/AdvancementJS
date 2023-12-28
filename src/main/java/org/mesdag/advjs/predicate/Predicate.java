package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.*;
import net.minecraft.predicate.entity.*;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;

import java.util.function.Consumer;

public class Predicate {
    public BlockPredicate block(JsonObject o) {
        return BlockPredicate.fromJson(o);
    }

    public BlockPredicate block(Consumer<BlockPredicateBuilder> consumer) {
        BlockPredicateBuilder builder = new BlockPredicateBuilder();
        consumer.accept(builder);
        return builder.builder.build();
    }

    public BlockPredicate block() {
        return BlockPredicate.ANY;
    }

    public DamagePredicate damage(JsonObject o) {
        return DamagePredicate.fromJson(o);
    }

    public DamagePredicate damage(Consumer<DamagePredicate.Builder> consumer) {
        DamagePredicate.Builder builder = new DamagePredicate.Builder();
        consumer.accept(builder);
        return builder.build();
    }

    public DamagePredicate damage() {
        return DamagePredicate.ANY;
    }

    public DamageSourcePredicate damageSource(JsonObject o) {
        return DamageSourcePredicate.fromJson(o);
    }

    public DamageSourcePredicate damageSource(Consumer<DamageSourcePredicate.Builder> consumer) {
        DamageSourcePredicate.Builder builder = DamageSourcePredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public DamageSourcePredicate damageSource() {
        return DamageSourcePredicate.EMPTY;
    }

    public DistancePredicate distance(JsonObject o) {
        return DistancePredicate.fromJson(o);
    }

    public DistancePredicate distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        return new DistancePredicate(builder.x, builder.y, builder.z, builder.horizontal, builder.absolute);
    }

    public DistancePredicate distance() {
        return DistancePredicate.ANY;
    }

    public EnchantmentPredicate enchantment(JsonObject o) {
        return EnchantmentPredicate.deserialize(o);
    }

    public EnchantmentPredicate enchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder = new EnchantmentPredicateBuilder();
        consumer.accept(builder);
        return new EnchantmentPredicate(builder.enchantment, builder.level);
    }

    public EnchantmentPredicate enchantment() {
        return EnchantmentPredicate.ANY;
    }

    public EnchantmentPredicate[] enchantmentArray(JsonArray o) {
        return EnchantmentPredicate.deserializeAll(o);
    }

    public EntityEquipmentPredicate entityEquipment(JsonObject o) {
        return EntityEquipmentPredicate.fromJson(o);
    }

    public EntityEquipmentPredicate entityEquipment(Consumer<EntityEquipmentPredicate.Builder> consumer) {
        EntityEquipmentPredicate.Builder builder = EntityEquipmentPredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityEquipmentPredicate entityEquipment() {
        return EntityEquipmentPredicate.ANY;
    }

    public EntityFlagsPredicate entityFlags(JsonObject o) {
        return EntityFlagsPredicate.fromJson(o);
    }

    public EntityFlagsPredicate entityFlags(Consumer<EntityFlagsPredicate.Builder> consumer) {
        EntityFlagsPredicate.Builder builder = EntityFlagsPredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityFlagsPredicate entityFlags() {
        return EntityFlagsPredicate.ANY;
    }

    public EntityPredicate entity(JsonObject o) {
        return EntityPredicate.fromJson(o);
    }

    public EntityPredicate entity(Consumer<EntityPredicate.Builder> consumer) {
        EntityPredicate.Builder builder = new EntityPredicate.Builder();
        consumer.accept(builder);
        return builder.build();
    }

    public EntityPredicate entity() {
        return EntityPredicate.ANY;
    }

    public FishingHookPredicate fishingHook(boolean isOpenWater) {
        return FishingHookPredicate.of(isOpenWater);
    }

    public FishingHookPredicate fishingHook() {
        return FishingHookPredicate.ANY;
    }

    public FluidPredicate fluid(JsonObject o) {
        return FluidPredicate.fromJson(o);
    }

    public FluidPredicate fluid(Consumer<FluidPredicate.Builder> consumer) {
        FluidPredicate.Builder builder = FluidPredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public FluidPredicate fluid() {
        return FluidPredicate.ANY;
    }

    public ItemPredicate item(JsonObject o) {
        return ItemPredicate.fromJson(o);
    }

    public ItemPredicate item(Consumer<ItemPredicate.Builder> consumer) {
        ItemPredicate.Builder builder = ItemPredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public ItemPredicate item() {
        return ItemPredicate.ANY;
    }

    public LightningBoltPredicate lightningBolt(JsonObject o) {
        return LightningBoltPredicate.fromJson(o);
    }

    public LightningBoltPredicate lightningBolt() {
        return LightningBoltPredicate.ANY;
    }

    public LightPredicate light(JsonObject o) {
        return LightPredicate.fromJson(o);
    }

    public LightPredicate light(Consumer<LightPredicate.Builder> consumer) {
        LightPredicate.Builder builder = new LightPredicate.Builder();
        consumer.accept(builder);
        return builder.build();
    }

    public LightPredicate light() {
        return LightPredicate.ANY;
    }

    public LocationPredicate location(JsonObject o) {
        return LocationPredicate.fromJson(o);
    }

    public LocationPredicate location(Consumer<LocationPredicate.Builder> consumer) {
        LocationPredicate.Builder builder = LocationPredicate.Builder.create();
        consumer.accept(builder);
        return builder.build();
    }

    public LocationPredicate location() {
        return LocationPredicate.ANY;
    }

    public EntityEffectPredicate mobEffects(JsonObject o) {
        return EntityEffectPredicate.fromJson(o);
    }

    public EntityEffectPredicate mobEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder = new MobEffectsPredicateBuilder();
        consumer.accept(builder);
        return new EntityEffectPredicate(builder.effects);
    }

    public EntityEffectPredicate mobEffects() {
        return EntityEffectPredicate.EMPTY;
    }

    public EntityEffectPredicate.EffectData mobEffectInstance(JsonObject o) {
        return EntityEffectPredicate.EffectData.fromJson(o);
    }

    public EntityEffectPredicate.EffectData mobEffectInstance(Consumer<MobEffectInstancePredicateBuilder> consumer) {
        MobEffectInstancePredicateBuilder builder = new MobEffectInstancePredicateBuilder();
        consumer.accept(builder);
        return new EntityEffectPredicate.EffectData(builder.amplifier, builder.duration, builder.ambient, builder.visible);
    }

    public EntityEffectPredicate.EffectData mobEffectInstance() {
        return new EntityEffectPredicate.EffectData();
    }

    public NbtPredicate nbt(NbtCompound nbt) {
        return new NbtPredicate(nbt);
    }

    public NbtPredicate nbt() {
        return NbtPredicate.ANY;
    }

    public PlayerPredicate player(JsonObject o) {
        return PlayerPredicate.fromJson(o);
    }

    public StatePredicate stateProperties(JsonObject o) {
        return StatePredicate.fromJson(o);
    }

    public StatePredicate stateProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder = new StatePropertiesPredicateBuilder();
        consumer.accept(builder);
        return builder.builder.build();
    }

    public StatePredicate stateProperties() {
        return StatePredicate.ANY;
    }
}
