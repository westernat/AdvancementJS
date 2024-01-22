package org.mesdag.advjs.predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.*;
import net.minecraft.nbt.CompoundTag;
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

    @Info("Any BlockPredicate")
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

    @Info("Any DamagePredicate")
    public DamagePredicate anyDamage() {
        return DamagePredicate.ANY;
    }

    public DamageSourcePredicate damageSourceFromJson(JsonObject o) {
        return DamageSourcePredicate.fromJson(o);
    }

    public DamageSourcePredicate damageSource(Consumer<DamageSourcePredicateBuilder> consumer) {
        DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any DamageSourcePredicate")
    public DamageSourcePredicate anyDamageSource() {
        return DamageSourcePredicate.ANY;
    }

    public DistancePredicate distanceFromJson(JsonObject o) {
        return DistancePredicate.fromJson(o);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public DistancePredicate distance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any DistancePredicate")
    public DistancePredicate anyDistance() {
        return DistancePredicate.ANY;
    }

    public EnchantmentPredicate enchantmentFromJson(JsonObject o) {
        return EnchantmentPredicate.fromJson(o);
    }

    public EnchantmentPredicate enchantment(Consumer<EnchantmentPredicateBuilder> consumer) {
        EnchantmentPredicateBuilder builder = new EnchantmentPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EnchantmentPredicate")
    public EnchantmentPredicate anyEnchantment() {
        return EnchantmentPredicate.ANY;
    }

    public EnchantmentPredicate[] enchantmentArray(JsonArray o) {
        return EnchantmentPredicate.fromJsonArray(o);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public EntityEquipmentPredicate entityEquipmentFromJson(JsonObject o) {
        return EntityEquipmentPredicate.fromJson(o);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public EntityEquipmentPredicate entityEquipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder = new EntityEquipmentPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EntityEquipmentPredicate")
    public EntityEquipmentPredicate anyEntityEquipment() {
        return EntityEquipmentPredicate.ANY;
    }

    public EntityFlagsPredicate entityFlagsFromJson(JsonObject o) {
        return EntityFlagsPredicate.fromJson(o);
    }

    public EntityFlagsPredicate entityFlags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder = new EntityFlagsPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any EntityFlagsPredicate")
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

    @Info("Any EntityPredicate")
    public EntityPredicate anyEntity() {
        return EntityPredicate.ANY;
    }

    public FluidPredicate fluidFromJson(JsonObject o) {
        return FluidPredicate.fromJson(o);
    }

    public FluidPredicate fluid(Consumer<FluidPredicateBuilder> consumer) {
        FluidPredicateBuilder builder = new FluidPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any FluidPredicate")
    public FluidPredicate anyFluid() {
        return FluidPredicate.ANY;
    }

    public ItemPredicate itemFromJson(JsonObject o) {
        return ItemPredicate.fromJson(o);
    }

    public ItemPredicate item(Consumer<ItemPredicateBuilder> consumer) {
        ItemPredicateBuilder builder = new ItemPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any ItemPredicate")
    public ItemPredicate anyItem() {
        return ItemPredicate.ANY;
    }

    public LightPredicate lightFromJson(JsonObject o) {
        return LightPredicate.fromJson(o);
    }

    public LightPredicate light(Bounds bounds) {
        return new LightPredicate.Builder().setComposite(bounds.toIntegerBounds()).build();
    }

    @Info("Any LightPredicate")
    public LightPredicate anyLight() {
        return LightPredicate.ANY;
    }

    public LocationPredicate locationFromJson(JsonObject o) {
        return LocationPredicate.fromJson(o);
    }

    public LocationPredicate location(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder = new LocationPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any LocationPredicate")
    public LocationPredicate anyLocation() {
        return LocationPredicate.ANY;
    }

    @Info("For testing the active status effects on the entity.")
    public MobEffectsPredicate mobEffectsFromJson(JsonObject o) {
        return MobEffectsPredicate.fromJson(o);
    }

    @Info("For testing the active status effects on the entity.")
    public MobEffectsPredicate mobEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder = new MobEffectsPredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any MobEffectsPredicate")
    public MobEffectsPredicate anyMobEffects() {
        return MobEffectsPredicate.ANY;
    }

    public MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstanceFromJson(JsonObject o) {
        return MobEffectsPredicate.MobEffectInstancePredicate.fromJson(o);
    }

    public MobEffectsPredicate.MobEffectInstancePredicate mobEffectInstance(Consumer<MobEffectInstancePredicateBuilder> consumer) {
        MobEffectInstancePredicateBuilder builder = new MobEffectInstancePredicateBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @Info("Any MobEffectInstancePredicate")
    public MobEffectsPredicate.MobEffectInstancePredicate anyMobEffectInstance() {
        return new MobEffectsPredicate.MobEffectInstancePredicate();
    }

    public NbtPredicate nbt(CompoundTag nbt) {
        return new NbtPredicate(nbt);
    }

    @Info("Any NbtPredicate")
    public NbtPredicate anyNbt() {
        return NbtPredicate.ANY;
    }

    public StatePropertiesPredicate statePropertiesFromJson(JsonObject o) {
        return StatePropertiesPredicate.fromJson(o);
    }

    public StatePropertiesPredicate stateProperties(Consumer<StatePropertiesPredicateBuilder> consumer) {
        StatePropertiesPredicateBuilder builder = new StatePropertiesPredicateBuilder();
        consumer.accept(builder);
        return builder.builder.build();
    }

    @Info("Any StatePropertiesPredicate")
    public StatePropertiesPredicate anyStateProperties() {
        return StatePropertiesPredicate.ANY;
    }

    public EntityTypePredicate entityTypeFromJson(JsonObject o) {
        return EntityTypePredicate.fromJson(o);
    }

    public EntityTypePredicate entityType(Consumer<EntityTypePredicateBuilder> consumer) {
        EntityTypePredicateBuilder builder = new EntityTypePredicateBuilder();
        consumer.accept(builder);
        return builder.predicate;
    }

    @Info("Any EntityTypePredicate")
    public EntityTypePredicate anyEntityType() {
        return EntityTypePredicate.ANY;
    }
}
