package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatVariant;
import net.minecraft.entity.passive.FrogVariant;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.entity.LightningBoltPredicate;
import net.minecraft.predicate.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

import static net.minecraft.entity.passive.CatVariant.*;
import static net.minecraft.entity.passive.FrogVariant.*;

public class EntityPredicateBuilder implements EntitySetter {
    final EntityPredicate.Builder builder = new EntityPredicate.Builder();
    public final TypeSpecific SPECIFIC = new TypeSpecific();

    @Info("Accept entity's id.")
    public void of(EntityType<?> entityType) {
        builder.type(entityType);
    }

    @Info("Accept entity's tag.")
    public void setTag(Identifier tag) {
        builder.type(TagKey.of(RegistryKeys.ENTITY_TYPE, tag));
    }

    @Info("Test this entity's type.")
    public void setTypeByPredicate(EntityTypePredicate entityTypePredicate) {
        builder.type(entityTypePredicate);
    }

    @Info("Test this entity's type.")
    public void setType(Consumer<EntityTypePredicateBuilder> consumer) {
        EntityTypePredicateBuilder builder1 = new EntityTypePredicateBuilder();
        consumer.accept(builder1);
        builder.type(builder1.predicate);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
        
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void setDistanceByPredicate(DistancePredicate distancePredicate) {
        builder.distance(distancePredicate);
    }

    @Info("""
        To test the distance to the entity this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void setDistance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder1 = new DistancePredicateBuilder();
        consumer.accept(builder1);
        builder.distance(builder1.build());
    }

    @Info("Test properties of this entity's location.")
    public void setLocationByPredicate(LocationPredicate locationPredicate) {
        builder.location(locationPredicate);
    }

    @Info("Test properties of this entity's location.")
    public void setLocation(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        builder.location(builder1.build());
    }

    @Info("Test properties of the block the entity is standing on, using a location predicate.")
    public void setSteppingOnByPredicate(LocationPredicate locationPredicate) {
        builder.steppingOn(locationPredicate);
    }

    @Info("Test properties of the block the entity is standing on, using a location predicate.")
    public void setSteppingOn(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        builder.steppingOn(builder1.build());
    }

    @Info("For testing the active status effects on the entity.")
    public void setEffectsByPredicate(EntityEffectPredicate mobEffectsPredicate) {
        builder.effects(mobEffectsPredicate);
    }

    @Info("For testing the active status effects on the entity.")
    public void setEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder1 = new MobEffectsPredicateBuilder();
        consumer.accept(builder1);
        builder.effects(builder1.build());
    }

    @Info("Test NBT data of this entity.")
    public void setNbt(NbtCompound nbt) {
        builder.nbt(new NbtPredicate(nbt));
    }

    @Info("To test flags of the entity.")
    public void setFlagsByPredicate(EntityFlagsPredicate entityFlagsPredicate) {
        builder.flags(entityFlagsPredicate);
    }

    @Info("To test flags of the entity.")
    public void setFlags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder1 = new EntityFlagsPredicateBuilder();
        consumer.accept(builder1);
        builder.flags(builder1.build());
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public void setEquipmentByPredicate(EntityEquipmentPredicate entityEquipmentPredicate) {
        builder.equipment(entityEquipmentPredicate);
    }

    @Info("For testing the items that this entity holds in its equipment slots.")
    public void setEquipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder1 = new EntityEquipmentPredicateBuilder();
        consumer.accept(builder1);
        builder.equipment(builder1.build());
    }

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void setVehicleByPredicate(EntityPredicate entityPredicate) {
        builder.vehicle(entityPredicate);
    }

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void setVehicle(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.vehicle(builder1.build());
    }

    @Info("Test properties of the vehicle entity that this entity is riding upon.")
    public void setVehicleByType(EntityType<?> entityType) {
        builder.vehicle(toEntity(entityType));
    }

    @Info("Test the entity directly riding this entity.")
    public void setPassengerByPredicate(EntityPredicate entityPredicate) {
        builder.passenger(entityPredicate);
    }

    @Info("Test the entity directly riding this entity.")
    public void setPassenger(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.passenger(builder1.build());
    }

    @Info("Test the entity directly riding this entity.")
    public void setPassengerByType(EntityType<?> entityType) {
        builder.passenger(toEntity(entityType));
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void setTargetedEntityByPredicate(EntityPredicate entityPredicate) {
        builder.targetedEntity(entityPredicate);
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void setTargetedEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.targetedEntity(builder1.build());
    }

    @Info("Test properties of the entity which this entity is targeting for attacks.")
    public void setTargetedEntityByType(EntityType<?> entityType) {
        builder.targetedEntity(toEntity(entityType));
    }

    @Info("Passes if the team of this entity matches this string.")
    public void setTeam(String team) {
        builder.team(team);
    }

    @Info("""
        To test entity properties that can only be applied to certain entity types.
                
        Supersedes lightning_bolt, player, fishing_hook, cat, etc.
        """)
    public void setTypeSpecific(TypeSpecific specific) {
        builder.typeSpecific(specific.subPredicate);
    }

    @HideFromJS
    public EntityPredicate build() {
        return builder.build();
    }

    static class TypeSpecific {
        TypeSpecificPredicate subPredicate;

        TypeSpecific setSubPredicate(TypeSpecificPredicate subPredicate) {
            this.subPredicate = subPredicate;
            return this;
        }

        @Info("To check information about this lightning bolt; fails when entity is not a lightning bolt.")
        public TypeSpecific lightningBoltFromJson(JsonObject o) {
            return new TypeSpecific().setSubPredicate(LightningBoltPredicate.fromJson(o));
        }

        @Info("To check information about this lightning bolt; fails when entity is not a lightning bolt.")
        public TypeSpecific lightningBolt(Consumer<LightningBoltPredicateBuilder> consumer) {
            LightningBoltPredicateBuilder builder = new LightningBoltPredicateBuilder();
            consumer.accept(builder);
            return new TypeSpecific().setSubPredicate(builder.predicate());
        }

        @Info("Any LightningBoltPredicate.")
        public TypeSpecific anyLightningBolt() {
            return new TypeSpecific().setSubPredicate(LightningBoltPredicate.ANY);
        }

        @Info("A simple player type.")
        public TypeSpecific playerFromJson(JsonObject o) {
            return new TypeSpecific().setSubPredicate(PlayerPredicate.fromJson(o));
        }

        @Info(value = "Test properties of the fishing hook that just got reeled in by this entity.",
            params = @Param(name = "isOpenWater"))
        public TypeSpecific fishingHook(boolean inOpenWater) {
            return new TypeSpecific().setSubPredicate(FishingHookPredicate.of(inOpenWater));
        }

        @Info("Any FishingHookPredicate.")
        public TypeSpecific anyFishingHook() {
            return new TypeSpecific().setSubPredicate(FishingHookPredicate.ANY);
        }

        @Info("A cat variant.")
        public TypeSpecific cat(CatType catType) {
            CatVariant variant = Registries.CAT_VARIANT.get(catType.variant);
            if (variant == null) {
                ConsoleJS.SERVER.warn("Failed to get cat type '%s'.".formatted(catType.name()));
                return new TypeSpecific();
            }
            return new TypeSpecific().setSubPredicate(TypeSpecificPredicate.cat(variant));
        }

        @Info("A frog variant.")
        public TypeSpecific frog(FrogType frogType) {
            return new TypeSpecific().setSubPredicate(TypeSpecificPredicate.frog(frogType.variant));
        }

        @Info("Test if the size of this slime matches a bounds.")
        public TypeSpecific slime(Bounds size) {
            return new TypeSpecific().setSubPredicate(SlimePredicate.of(size.toIntegerBounds()));
        }
    }

    public enum CatType {
        all_black(ALL_BLACK),
        black(BLACK),
        british_shorthair(BRITISH_SHORTHAIR),
        calico(CALICO),
        jellie(JELLIE),
        persian(PERSIAN),
        ragdoll(RAGDOLL),
        red(RED),
        siamese(SIAMESE),
        tabby(TABBY),
        white(WHITE);

        @HideFromJS
        public final RegistryKey<CatVariant> variant;

        CatType(RegistryKey<CatVariant> variant) {
            this.variant = variant;
        }
    }

    public enum FrogType {
        temperate(TEMPERATE),
        warm(WARM),
        cold(COLD);

        final FrogVariant variant;

        FrogType(FrogVariant variant) {
            this.variant = variant;
        }
    }
}
