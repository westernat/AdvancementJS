package org.mesdag.advjs.predicate;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.CatVariant;
import net.minecraft.world.entity.animal.FrogVariant;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

import static net.minecraft.world.entity.animal.CatVariant.*;
import static net.minecraft.world.entity.animal.FrogVariant.*;

public class EntityPredicateBuilder implements EntitySetter {
    final EntityPredicate.Builder builder = new EntityPredicate.Builder();
    public final TypeSpecific SPECIFIC = new TypeSpecific();

    @Info("Accept entity's id.")
    public void of(EntityType<?> entityType) {
        builder.of(entityType);
    }

    @Info("Accept entity's tag.")
    public void setTag(ResourceLocation tag) {
        builder.of(TagKey.create(Registries.ENTITY_TYPE, tag));
    }

    @Info("Test this entity's type.")
    public void setTypeByPredicate(EntityTypePredicate entityTypePredicate) {
        builder.entityType(entityTypePredicate);
    }

    @Info("Test this entity's type.")
    public void setType(Consumer<EntityTypePredicateBuilder> consumer) {
        EntityTypePredicateBuilder builder1 = new EntityTypePredicateBuilder();
        consumer.accept(builder1);
        builder.entityType(builder1.predicate);
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
        builder.located(locationPredicate);
    }

    @Info("Test properties of this entity's location.")
    public void setLocation(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        builder.located(builder1.build());
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
    public void setEffectsByPredicate(MobEffectsPredicate mobEffectsPredicate) {
        builder.effects(mobEffectsPredicate);
    }

    @Info("For testing the active status effects on the entity.")
    public void setEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder1 = new MobEffectsPredicateBuilder();
        consumer.accept(builder1);
        builder.effects(builder1.build());
    }

    @Info("Test NBT data of this entity.")
    public void setNbt(CompoundTag nbt) {
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
        builder.subPredicate(specific.subPredicate);
    }

    @HideFromJS
    public EntityPredicate build() {
        return builder.build();
    }

    static class TypeSpecific {
        EntitySubPredicate subPredicate;

        TypeSpecific setSubPredicate(EntitySubPredicate subPredicate) {
            this.subPredicate = subPredicate;
            return this;
        }

        @Info("To check information about this lightning bolt; fails when entity is not a lightning bolt.")
        public TypeSpecific lightningBoltFromJson(JsonObject o) {
            return new TypeSpecific().setSubPredicate(LighthingBoltPredicate.fromJson(o));
        }

        @Info("To check information about this lightning bolt; fails when entity is not a lightning bolt.")
        public TypeSpecific lightningBolt(Consumer<LightningBoltPredicateBuilder> consumer) {
            LightningBoltPredicateBuilder builder = new LightningBoltPredicateBuilder();
            consumer.accept(builder);
            return new TypeSpecific().setSubPredicate(builder.predicate());
        }

        @Info("Any LightningBoltPredicate.")
        public TypeSpecific anyLightningBolt() {
            return new TypeSpecific().setSubPredicate(LighthingBoltPredicate.ANY);
        }

        @Info("A simple player type.")
        public TypeSpecific playerFromJson(JsonObject o) {
            return new TypeSpecific().setSubPredicate(PlayerPredicate.fromJson(o));
        }

        @Info(value = "Test properties of the fishing hook that just got reeled in by this entity.",
            params = @Param(name = "isOpenWater"))
        public TypeSpecific fishingHook(boolean inOpenWater) {
            return new TypeSpecific().setSubPredicate(FishingHookPredicate.inOpenWater(inOpenWater));
        }

        @Info("Any FishingHookPredicate.")
        public TypeSpecific anyFishingHook() {
            return new TypeSpecific().setSubPredicate(FishingHookPredicate.ANY);
        }

        @Info("A cat variant.")
        public TypeSpecific cat(CatType catType) {
            CatVariant variant = BuiltInRegistries.CAT_VARIANT.get(catType.variant);
            if (variant == null) {
                ConsoleJS.SERVER.error("Failed to get cat type '%s'".formatted(catType.name()));
                return new TypeSpecific();
            }
            return new TypeSpecific().setSubPredicate(EntitySubPredicate.variant(variant));
        }

        @Info("A frog variant.")
        public TypeSpecific frog(FrogType frogType) {
            return new TypeSpecific().setSubPredicate(EntitySubPredicate.variant(frogType.variant));
        }

        @Info("Test if the size of this slime matches a bounds.")
        public TypeSpecific slime(Bounds size) {
            return new TypeSpecific().setSubPredicate(SlimePredicate.sized(size.toIntegerBounds()));
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
        public final ResourceKey<CatVariant> variant;

        CatType(ResourceKey<CatVariant> variant) {
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
