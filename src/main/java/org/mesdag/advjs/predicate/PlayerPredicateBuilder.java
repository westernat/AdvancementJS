package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class PlayerPredicateBuilder {
    final PlayerPredicate.Builder playerBuilder = new PlayerPredicate.Builder();
    DistancePredicate distance = DistancePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    LocationPredicate steppingOn = LocationPredicate.ANY;
    EntityEffectPredicate effects = EntityEffectPredicate.EMPTY;
    NbtPredicate nbt = NbtPredicate.ANY;
    EntityFlagsPredicate flags = EntityFlagsPredicate.ANY;
    EntityEquipmentPredicate equipment = EntityEquipmentPredicate.ANY;
    EntityPredicate vehicle = EntityPredicate.ANY;
    EntityPredicate passenger = EntityPredicate.ANY;
    EntityPredicate targetedEntity = EntityPredicate.ANY;
    @Nullable
    String team = null;

    @Info("Test the experience level of this player.")
    public void setLevel(NumberRange.IntRange bounds) {
        playerBuilder.experienceLevel(bounds);
    }

    @Info(value = "Test player's statistics.",
        params = {
            @Param(name = "statId", value = "The statistic ID to test."),
            @Param(name = "statType", value = "The statistic type."),
            @Param(name = "value", value = "Test if the value of the statistic matches an exact number.")
        })
    public void addStat(Identifier statId, StatType<?> statType, Bounds bounds) {
        playerBuilder.stat(getStat(statType, statId), bounds.toIntegerBounds());
    }

    private static <T> Stat<T> getStat(StatType<T> statType, Identifier statId) {
        Registry<T> registry = statType.getRegistry();
        T t = registry.get(statId);
        if (t == null) {
            throw new RuntimeException("Unknown object " + statId + " for stat type " + Registries.STAT_TYPE.getKey(statType));
        } else {
            return statType.getOrCreateStat(t);
        }
    }

    @Info(value = "Test if recipes are known or unknown to this player.",
        params = {
            @Param(name = "recipeId"),
            @Param(name = "unlocked")
        })
    public void addRecipe(Identifier recipeId, boolean unlocked) {
        playerBuilder.recipe(recipeId, unlocked);
    }

    @Info("Test the game mode of this player.")
    public void setGameType(GameMode gameType) {
        playerBuilder.gameMode(gameType);
    }

    @Info("""
        Test properties of the entity that this player is looking at, as long as it is visible and within a radius of 100 blocks.

        Visibility is defined through the line from the player's eyes to the entity's eyes, rather than the direction that the player is looking in.
        """)
    public void setLookingAt(EntityPredicate entityPredicate) {
        playerBuilder.lookingAt(entityPredicate);
    }

    @Info("""
        Test properties of the entity that this player is looking at, as long as it is visible and within a radius of 100 blocks.
                
        Visibility is defined through the line from the player's eyes to the entity's eyes, rather than the direction that the player is looking in.
        """)
    public void setLookingAt(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder = new EntityPredicateBuilder();
        consumer.accept(builder);
        playerBuilder.lookingAt(builder.build());
    }

    @Info(value = "Test if the player's advancement done.",
        params = {
            @Param(name = "advancement"),
            @Param(name = "done")
        })
    public void checkAdvancementDone(Identifier advancement, boolean done) {
        playerBuilder.advancement(advancement, done);
    }

    //TODO checkAdvancementCriteria

    @Info("""
        Test the distance to player this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("""
        Test the distance to player this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void setDistance(Consumer<DistancePredicateBuilder> consumer) {
        DistancePredicateBuilder builder = new DistancePredicateBuilder();
        consumer.accept(builder);
        this.distance = builder.build();
    }

    @Info("Test properties of player's location.")
    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    @Info("Test properties of player's location.")
    public void setLocation(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        this.location = builder1.build();
    }

    @Info("Test properties of the block player is standing on, using a location predicate.")
    public void setSteppingOn(LocationPredicate steppingOn) {
        this.steppingOn = steppingOn;
    }

    @Info("Test properties of the block player is standing on, using a location predicate.")
    public void setSteppingOn(Consumer<LocationPredicateBuilder> consumer) {
        LocationPredicateBuilder builder1 = new LocationPredicateBuilder();
        consumer.accept(builder1);
        this.steppingOn = builder1.build();
    }

    @Info("Test the active status effects on player.")
    public void setEffects(EntityEffectPredicate effects) {
        this.effects = effects;
    }

    @Info("Test the active status effects on player.")
    public void setEffects(Consumer<MobEffectsPredicateBuilder> consumer) {
        MobEffectsPredicateBuilder builder1 = new MobEffectsPredicateBuilder();
        consumer.accept(builder1);
        this.effects = builder1.build();
    }

    @Info("Test NBT data of player.")
    public void setNbt(NbtCompound nbt) {
        this.nbt = new NbtPredicate(nbt);
    }

    @Info("Test flags of player.")
    public void setFlags(EntityFlagsPredicate flags) {
        this.flags = flags;
    }

    @Info("Test flags of player.")
    public void setFlags(Consumer<EntityFlagsPredicateBuilder> consumer) {
        EntityFlagsPredicateBuilder builder1 = new EntityFlagsPredicateBuilder();
        consumer.accept(builder1);
        this.flags = builder1.build();
    }

    @Info("Test the items that player holds in its equipment slots.")
    public void setEquipment(EntityEquipmentPredicate equipment) {
        this.equipment = equipment;
    }

    @Info("Test the items that player holds in its equipment slots.")
    public void setEquipment(Consumer<EntityEquipmentPredicateBuilder> consumer) {
        EntityEquipmentPredicateBuilder builder1 = new EntityEquipmentPredicateBuilder();
        consumer.accept(builder1);
        this.equipment = builder1.build();
    }

    @Info("Test properties of the vehicle entity that player is riding upon.")
    public void setVehicle(EntityPredicate vehicle) {
        this.vehicle = vehicle;
    }

    @Info("Test properties of the vehicle entity that player is riding upon.")
    public void setVehicle(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        this.vehicle = builder1.build();
    }

    @Info("Test the entity directly riding player.")
    public void setPassenger(EntityPredicate passenger) {
        this.passenger = passenger;
    }

    @Info("Test the entity directly riding player.")
    public void setPassenger(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        this.passenger = builder1.build();
    }

    @Info("Test properties of the entity which player is targeting for attacks.")
    public void setTargetedEntity(EntityPredicate targetedEntity) {
        this.targetedEntity = targetedEntity;
    }

    @Info("Test properties of the entity which player is targeting for attacks.")
    public void setTargetedEntity(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        this.targetedEntity = builder1.build();
    }

    @Info("Passes if the team of player matches this string.")
    public void setTeam(@Nullable String team) {
        this.team = team;
    }

    @HideFromJS
    public LootContextPredicate build() {
        return EntityPredicate.asLootContextPredicate(EntityPredicate.Builder.create()
            .typeSpecific(playerBuilder.build())
            .distance(distance)
            .location(location)
            .steppingOn(steppingOn)
            .effects(effects)
            .nbt(nbt)
            .flags(flags)
            .equipment(equipment)
            .vehicle(vehicle)
            .passenger(passenger)
            .targetedEntity(targetedEntity)
            .team(team)
            .build());
    }
}
