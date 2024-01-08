package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
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

    @Info("Any BlockPredicate")
    public BlockPredicate block() {
        return BlockPredicate.ANY;
    }

    public DamagePredicate damage(JsonObject o) {
        return DamagePredicate.fromJson(o);
    }

    public DamagePredicate damage(Consumer<DamagePredicateBuilder> consumer) {
        DamagePredicateBuilder builder = new DamagePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any DamagePredicate")
    public DamagePredicate damage() {
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

    @Info("Any DamageSourcePredicate")
    public DamageSourcePredicate damageSource() {
        return DamageSourcePredicate.EMPTY;
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public DistancePredicate distance(JsonObject o) {
        return DistancePredicate.fromJson(o);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public DistancePredicate distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        return new DistancePredicate(builder.x, builder.y, builder.z, builder.horizontal, builder.absolute);
    }

    @Info("Any DistancePredicate")
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

    @Info("Any EnchantmentPredicate")
    public EnchantmentPredicate enchantment() {
        return EnchantmentPredicate.ANY;
    }

    public EnchantmentPredicate[] enchantmentArray(JsonArray o) {
        return EnchantmentPredicate.deserializeAll(o);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public EntityEquipmentPredicate entityEquipment(JsonObject o) {
        return EntityEquipmentPredicate.fromJson(o);
    }

    @Info(value = "For testing the items that this entity holds in its equipment slots.",
        params = {
            @Param(name = "mainhand", value = "Test the item in the entity's main hand."),
            @Param(name = "offhand", value = "Test the item in the entity's offhand."),
            @Param(name = "head", value = "Test the item in the entity's head armor slot."),
            @Param(name = "chest", value = "Test the item in the entity's chest armor slot."),
            @Param(name = "legs", value = "Test the item in the entity's legs armor slot."),
            @Param(name = "feet", value = "Test the item in the entity's feet armor slot.")
        })
    public EntityEquipmentPredicate entityEquipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder =new EntityEquipmentPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EntityEquipmentPredicate")
    public EntityEquipmentPredicate entityEquipment() {
        return EntityEquipmentPredicate.ANY;
    }

    public EntityFlagsPredicate entityFlags(JsonObject o) {
        return EntityFlagsPredicate.fromJson(o);
    }

    public EntityFlagsPredicate entityFlags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder =new EntityFlagsPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EntityFlagsPredicate")
    public EntityFlagsPredicate entityFlags() {
        return EntityFlagsPredicate.ANY;
    }

    public EntityPredicate entity(JsonObject o) {
        return EntityPredicate.fromJson(o);
    }

    public EntityPredicate entity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EntityPredicate")
    public EntityPredicate entity() {
        return EntityPredicate.ANY;
    }

    @Info(value = "Check if fishing hook is in open water.",
        params = @Param(name = "isOpenWater"))
    public FishingHookPredicate fishingHook(boolean isOpenWater) {
        return FishingHookPredicate.of(isOpenWater);
    }

    @Info("Any EntityPredicate")
    public FishingHookPredicate fishingHook() {
        return FishingHookPredicate.ALL;
    }

    public FluidPredicate fluid(JsonObject o) {
        return FluidPredicate.fromJson(o);
    }

    public FluidPredicate fluid(Consumer<FluidPredicateBuilder> consumer) {
        FluidPredicateBuilder builder =new FluidPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any FluidPredicate")
    public FluidPredicate fluid() {
        return FluidPredicate.ANY;
    }

    public ItemPredicate item(JsonObject o) {
        return ItemPredicate.fromJson(o);
    }

    public ItemPredicate item(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder =new ItemPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any ItemPredicate")
    public ItemPredicate item() {
        return ItemPredicate.ANY;
    }

    public LightningBoltPredicate lightningBolt(JsonObject o) {
        return LightningBoltPredicate.fromJson(o);
    }

    public LightningBoltPredicate lightningBolt(Consumer<LightningBoltPredicateBuilder> consumer){
        LightningBoltPredicateBuilder builder = new LightningBoltPredicateBuilder();
        consumer.accept(builder);
        return LightningBoltPredicate.fromJson(builder.toJson());
    }

    @Info("Any LightningBoltPredicate")
    public LightningBoltPredicate lightningBolt() {
        return LightningBoltPredicate.of(NumberRange.IntRange.ANY);
    }

    public LightPredicate light(JsonObject o) {
        return LightPredicate.fromJson(o);
    }

    public LightPredicate light(NumberRange.IntRange bounds) {
        return new LightPredicate.Builder().light(bounds).build();
    }

    @Info("Any LightPredicate")
    public LightPredicate light() {
        return LightPredicate.ANY;
    }

    public LocationPredicate location(JsonObject o) {
        return LocationPredicate.fromJson(o);
    }

    public LocationPredicate location(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder =new LocationPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any LocationPredicate")
    public LocationPredicate location() {
        return LocationPredicate.ANY;
    }

    @Info("For testing the active status effects on the entity.")
    public EntityEffectPredicate mobEffects(JsonObject o) {
        return EntityEffectPredicate.fromJson(o);
    }

    @Info("For testing the active status effects on the entity.")
    public EntityEffectPredicate mobEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder = new MobEffectsPredicateBuilder();
        consumer.accept(builder);
        return new EntityEffectPredicate(builder.effects);
    }

    @Info("Any MobEffectsPredicate")
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

    @Info("Any NbtPredicate")
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

    @Info("Any StatePropertiesPredicate")
    public StatePredicate stateProperties() {
        return StatePredicate.ANY;
    }
}
