package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.*;
import net.minecraft.core.Registry;
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

    public void setLevel(MinMaxBounds.Ints bounds) {
        playerBuilder.setLevel(bounds);
    }

    public void addStat(ResourceLocation statId, StatType<?> statType, MinMaxBounds.Ints bounds) {
        playerBuilder.addStat(getStat(statType, statId), bounds);
    }

    private static <T> Stat<T> getStat(StatType<T> statType, ResourceLocation statId) {
        Registry<T> registry = statType.getRegistry();
        T t = registry.get(statId);
        if (t == null) {
            throw new RuntimeException("Unknown object " + statId + " for stat type " + Registry.STAT_TYPE.getKey(statType));
        } else {
            return statType.get(t);
        }
    }

    public void addRecipe(ResourceLocation recipe, boolean unlocked) {
        playerBuilder.addRecipe(recipe, unlocked);
    }

    public void setGameType(GameType gameType) {
        playerBuilder.setGameType(gameType);
    }

    public void setLookingAt(EntityPredicate entityPredicate) {
        playerBuilder.setLookingAt(entityPredicate);
    }

    public void checkAdvancementDone(ResourceLocation advancement, boolean done) {
        playerBuilder.checkAdvancementDone(advancement, done);
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

    public void setEffects(MobEffectsPredicate effects) {
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

    EntityPredicate.Composite build() {
        return EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity()
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
