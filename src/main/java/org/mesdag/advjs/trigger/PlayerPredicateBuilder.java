package org.mesdag.advjs.trigger;


import net.minecraft.predicate.NbtPredicate;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.*;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.Nullable;

class PlayerPredicateBuilder {
    final PlayerPredicate.Builder playerBuilder = new PlayerPredicate.Builder();
    DistancePredicate distance = DistancePredicate.ANY;
    LocationPredicate location = LocationPredicate.ANY;
    LocationPredicate steppingOnLocation = LocationPredicate.ANY;
    EntityEffectPredicate effects = EntityEffectPredicate.EMPTY;
    NbtPredicate nbt = NbtPredicate.ANY;
    EntityFlagsPredicate flags = EntityFlagsPredicate.ANY;
    EntityEquipmentPredicate equipment = EntityEquipmentPredicate.ANY;
    EntityPredicate vehicle = EntityPredicate.ANY;
    EntityPredicate passenger = EntityPredicate.ANY;
    EntityPredicate targetedEntity = EntityPredicate.ANY;
    @Nullable
    String team = null;

    public void setLevel(NumberRange.IntRange bounds) {
        playerBuilder.experienceLevel(bounds);
    }

    public void addStat(Identifier statId, StatType<?> statType, NumberRange.IntRange bounds) {
        playerBuilder.stat(getStat(statType, statId), bounds);
    }

    private static <T> Stat<T> getStat(StatType<T> statType, Identifier statId) {
        Registry<T> registry = statType.getRegistry();
        T t = registry.get(statId);
        if (t == null) {
            throw new RuntimeException("Unknown object " + statId + " for stat type " + Registry.STAT_TYPE.getKey(statType));
        } else {
            return statType.getOrCreateStat(t);
        }
    }

    public void addRecipe(Identifier recipe, boolean unlocked) {
        playerBuilder.recipe(recipe, unlocked);
    }

    public void setGameType(GameMode gameType) {
        playerBuilder.gameMode(gameType);
    }

    public void setLookingAt(EntityPredicate entityPredicate) {
        playerBuilder.lookingAt(entityPredicate);
    }

    public void checkAdvancementDone(Identifier advancement, boolean done) {
        playerBuilder.advancement(advancement, done);
    }

    //TODO checkAdvancementCriteria

    public void setDistance(DistancePredicate distance) {
        this.distance = distance;
    }

    public void setLocation(LocationPredicate location) {
        this.location = location;
    }

    public void setSteppingOnLocation(LocationPredicate steppingOnLocation) {
        this.steppingOnLocation = steppingOnLocation;
    }

    public void setEffects(EntityEffectPredicate effects) {
        this.effects = effects;
    }

    public void setNbt(NbtPredicate nbt) {
        this.nbt = nbt;
    }

    public void setFlags(EntityFlagsPredicate flags) {
        this.flags = flags;
    }

    public void setEquipment(EntityEquipmentPredicate equipment) {
        this.equipment = equipment;
    }

    public void setVehicle(EntityPredicate vehicle) {
        this.vehicle = vehicle;
    }

    public void setPassenger(EntityPredicate passenger) {
        this.passenger = passenger;
    }

    public void setTargetedEntity(EntityPredicate targetedEntity) {
        this.targetedEntity = targetedEntity;
    }

    public void setTeam(@Nullable String team) {
        this.team = team;
    }

    EntityPredicate.Extended build() {
        return EntityPredicate.Extended.ofLegacy(EntityPredicate.Builder.create()
            .typeSpecific(playerBuilder.build())
            .distance(distance)
            .location(location)
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
