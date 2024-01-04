package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;
import net.minecraft.world.level.GameType;

import javax.annotation.Nullable;

class PlayerPredicateBuilder {
    final PlayerPredicate.Builder playerBuilder = new PlayerPredicate.Builder();
    DistancePredicate distance = DistancePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    LocationPredicate steppingOnLocation = LocationPredicate.ANY;
    MobEffectsPredicate effects = MobEffectsPredicate.ANY;
    NbtPredicate nbt = NbtPredicate.ANY;
    EntityFlagsPredicate flags = EntityFlagsPredicate.ANY;
    EntityEquipmentPredicate equipment = EntityEquipmentPredicate.ANY;
    EntityPredicate vehicle = EntityPredicate.ANY;
    EntityPredicate passenger = EntityPredicate.ANY;
    EntityPredicate targetedEntity = EntityPredicate.ANY;
    @Nullable
    String team = null;

    @Info("Test the experience level of this player.")
    public void setLevel(MinMaxBounds.Ints bounds) {
        playerBuilder.setLevel(bounds);
    }

    @Info(value = "Test player's statistics.",
        params = {
            @Param(name = "statId", value = "The statistic ID to test."),
            @Param(name = "statType", value = "The statistic type."),
            @Param(name = "value", value = "Test if the value of the statistic matches an exact number.")
        })
    public void addStat(ResourceLocation statId, StatType<?> statType, MinMaxBounds.Ints value) {
        playerBuilder.addStat(getStat(statType, statId), value);
    }

    private static <T> Stat<T> getStat(StatType<T> statType, ResourceLocation statId) {
        Registry<T> registry = statType.getRegistry();
        T t = registry.get(statId);
        if (t == null) {
            throw new RuntimeException("Unknown object " + statId + " for stat type " + BuiltInRegistries.STAT_TYPE.get(statId));
        } else {
            return statType.get(t);
        }
    }

    @Info("Test if recipes are known or unknown to this player.")
    public void addRecipe(ResourceLocation recipe, boolean unlocked) {
        playerBuilder.addRecipe(recipe, unlocked);
    }

    @Info("Test the game mode of this player.")
    public void setGameType(GameType gameType) {
        playerBuilder.setGameType(gameType);
    }

    @Info("""
        Test properties of the entity that this player is looking at, as long as it is visible and within a radius of 100 blocks.
        
        Visibility is defined through the line from the player's eyes to the entity's eyes, rather than the direction that the player is looking in.
        """)
    public void setLookingAt(EntityPredicate entityPredicate) {
        playerBuilder.setLookingAt(entityPredicate);
    }


    @Info("Test if the player's advancement done.")
    public void checkAdvancementDone(ResourceLocation advancement, boolean done) {
        playerBuilder.checkAdvancementDone(advancement, done);
    }

    //TODO checkAdvancementCriteria

    @Info("""
        Test the distance to player this predicate is invoked upon.
                
        Passes if the calculated distance is between the entered min and max, inclusive.
        """)
    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    @Info("Test properties of player's location.")
    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    @Info("Test properties of the block player is standing on, using a location predicate.")
    public void setSteppingOnLocation(LocationPredicate steppingOnLocation) {
        this.steppingOnLocation = steppingOnLocation;
    }

    @Info("Test the active status effects on player.")
    public void setEffects(MobEffectsPredicate effects) {
        this.effects = effects;
    }

    @Info("Test NBT data of player.")
    public void setNbt(NbtPredicate nbt) {
        this.nbt = nbt;
    }

    @Info("Test flags of player.")
    public void setFlags(EntityFlagsPredicate flags) {
        this.flags = flags;
    }

    @Info("Test the items that player holds in its equipment slots.")
    public void setEquipment(EntityEquipmentPredicate equipment) {
        this.equipment = equipment;
    }

    @Info("Test properties of the vehicle entity that player is riding upon.")
    public void setVehicle(EntityPredicate vehicle) {
        this.vehicle = vehicle;
    }

    @Info("Test the entity directly riding player.")
    public void setPassenger(EntityPredicate passenger) {
        this.passenger = passenger;
    }

    @Info("Test properties of the entity which player is targeting for attacks.")
    public void setTargetedEntity(EntityPredicate targetedEntity) {
        this.targetedEntity = targetedEntity;
    }

    @Info("Passes if the team of player matches this string.")
    public void setTeam(@Nullable String team) {
        this.team = team;
    }

    ContextAwarePredicate build() {
        return EntityPredicate.wrap(EntityPredicate.Builder.entity()
            .subPredicate(playerBuilder.build())
            .distance(distance)
            .located(location)
            .steppingOn(steppingOnLocation)
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
