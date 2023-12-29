package org.mesdag.advjs.trigger;

import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.entity.DistancePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;

import java.util.function.Consumer;

public class Trigger {
    public BeeNestDestroyedCriterion.Conditions destroyBlock(Consumer<DestroyBlockBuilder> consumer) {
        DestroyBlockBuilder builder = new DestroyBlockBuilder();
        consumer.accept(builder);
        return new BeeNestDestroyedCriterion.Conditions(builder.player, builder.block, builder.item, builder.bounds);
    }

    public BredAnimalsCriterion.Conditions bredAnimals(Consumer<BredAnimalsBuilder> consumer) {
        BredAnimalsBuilder builder = new BredAnimalsBuilder();
        consumer.accept(builder);
        return new BredAnimalsCriterion.Conditions(builder.player, builder.parent, builder.partner, builder.child);
    }

    public ChangedDimensionCriterion.Conditions changedDimension(Consumer<ChangeDimensionBuilder> consumer) {
        ChangeDimensionBuilder builder = new ChangeDimensionBuilder();
        consumer.accept(builder);
        return new ChangedDimensionCriterion.Conditions(builder.player, builder.from, builder.to);
    }

    public ChanneledLightningCriterion.Conditions channeledLightning(Consumer<ChanneledLightningBuilder> consumer) {
        ChanneledLightningBuilder builder = new ChanneledLightningBuilder();
        consumer.accept(builder);
        return new ChanneledLightningCriterion.Conditions(builder.player, builder.victims);
    }

    public ConstructBeaconCriterion.Conditions constructedBeacon(Consumer<ConstructBeaconBuilder> consumer) {
        ConstructBeaconBuilder builder = new ConstructBeaconBuilder();
        consumer.accept(builder);
        return new ConstructBeaconCriterion.Conditions(builder.player, builder.bounds);
    }

    public CuredZombieVillagerCriterion.Conditions curedZombieVillager(Consumer<CuredZombieVillagerBuilder> consumer) {
        CuredZombieVillagerBuilder builder = new CuredZombieVillagerBuilder();
        consumer.accept(builder);
        return new CuredZombieVillagerCriterion.Conditions(builder.player, builder.zombie, builder.villager);
    }

    public ConsumeItemCriterion.Conditions consumeItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ConsumeItemCriterion.Conditions(builder.player, builder.item);
    }

    public TravelCriterion.Conditions fallFromHeight(EntityPredicate entityPredicate, DistancePredicate distancePredicate, LocationPredicate locationPredicate) {
        return new TravelCriterion.Conditions(Criteria.FALL_FROM_HEIGHT.getId(), EntityPredicate.Extended.ofLegacy(entityPredicate), locationPredicate, distancePredicate);
    }

    public TravelCriterion.Conditions rideEntityInLava(EntityPredicate entityPredicate, DistancePredicate distancePredicate) {
        return new TravelCriterion.Conditions(Criteria.RIDE_ENTITY_IN_LAVA.getId(), EntityPredicate.Extended.ofLegacy(entityPredicate), LocationPredicate.ANY, distancePredicate);
    }

    public TravelCriterion.Conditions travelledThroughNether(DistancePredicate distancePredicate) {
        return new TravelCriterion.Conditions(Criteria.NETHER_TRAVEL.getId(), EntityPredicate.Extended.EMPTY, LocationPredicate.ANY, distancePredicate);
    }

    public EffectsChangedCriterion.Conditions effectChanged(Consumer<EffectsChangedBuilder> consumer) {
        EffectsChangedBuilder builder = new EffectsChangedBuilder();
        consumer.accept(builder);
        return new EffectsChangedCriterion.Conditions(builder.player, builder.effects, builder.source);
    }

    public EnchantedItemCriterion.Conditions enchantedItem(Consumer<EnchantedItemBuilder> consumer) {
        EnchantedItemBuilder builder = new EnchantedItemBuilder();
        consumer.accept(builder);
        return new EnchantedItemCriterion.Conditions(builder.player, builder.item, builder.levels);
    }

    public EnterBlockCriterion.Conditions entersBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new EnterBlockCriterion.Conditions(builder.player, builder.block, builder.state);
    }

    public EntityHurtPlayerCriterion.Conditions entityHurtPlayer(Consumer<EntityHurtPlayerBuilder> consumer) {
        EntityHurtPlayerBuilder builder = new EntityHurtPlayerBuilder();
        consumer.accept(builder);
        return new EntityHurtPlayerCriterion.Conditions(builder.player, builder.damage);
    }

    public FilledBucketCriterion.Conditions filledBucket(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new FilledBucketCriterion.Conditions(builder.player, builder.item);
    }

    public FishingRodHookedCriterion.Conditions fishingRodHooked(Consumer<FishingRodHookedBuilder> consumer) {
        FishingRodHookedBuilder builder = new FishingRodHookedBuilder();
        consumer.accept(builder);
        return new FishingRodHookedCriterion.Conditions(builder.player, builder.rod, builder.entity, builder.item);
    }

    public InventoryChangedCriterion.Conditions inventoryChange(Consumer<InventoryChangeBuilder> consumer) {
        InventoryChangeBuilder builder = new InventoryChangeBuilder();
        consumer.accept(builder);
        return new InventoryChangedCriterion.Conditions(builder.player, builder.slotsOccupied, builder.slotsFull, builder.slotsEmpty, builder.items);
    }

    public ItemDurabilityChangedCriterion.Conditions itemDurability(Consumer<ItemDurabilityBuilder> consumer) {
        ItemDurabilityBuilder builder = new ItemDurabilityBuilder();
        consumer.accept(builder);
        return new ItemDurabilityChangedCriterion.Conditions(builder.player, builder.item, builder.durability, builder.delta);
    }

    public ThrownItemPickedUpByEntityCriterion.Conditions itemPickedUpByEntity(Consumer<ItemPickedUpByEntityBuilder> consumer) {
        ItemPickedUpByEntityBuilder builder = new ItemPickedUpByEntityBuilder();
        consumer.accept(builder);
        return new ThrownItemPickedUpByEntityCriterion.Conditions(builder.player, builder.item, builder.entity);
    }

    public ItemUsedOnBlockCriterion.Conditions itemUsedOnBlock(Consumer<ItemUsedOnBlockBuilder> consumer) {
        ItemUsedOnBlockBuilder builder = new ItemUsedOnBlockBuilder();
        consumer.accept(builder);
        return new ItemUsedOnBlockCriterion.Conditions(builder.player, builder.location, builder.item);
    }

    public KilledByCrossbowCriterion.Conditions killedByCrossbow(Consumer<KilledByCrossbowBuilder> consumer) {
        KilledByCrossbowBuilder builder = new KilledByCrossbowBuilder();
        consumer.accept(builder);
        return new KilledByCrossbowCriterion.Conditions(builder.player, builder.victims, builder.uniqueEntityTypes);
    }

    public OnKilledCriterion.Conditions playerKilledEntity(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new OnKilledCriterion.Conditions(Criteria.PLAYER_KILLED_ENTITY.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    public OnKilledCriterion.Conditions entityKilledPlayer(Consumer<EntityKillPlayerBuilder> consumer) {
        EntityKillPlayerBuilder builder = new EntityKillPlayerBuilder();
        consumer.accept(builder);
        return new OnKilledCriterion.Conditions(Criteria.ENTITY_KILLED_PLAYER.getId(), builder.player, builder.killer, builder.killingBlow);
    }

    public LevitationCriterion.Conditions levitated(Consumer<LevitationBuilder> consumer) {
        LevitationBuilder builder = new LevitationBuilder();
        consumer.accept(builder);
        return new LevitationCriterion.Conditions(builder.player, builder.distance, builder.duration);
    }

    public LightningStrikeCriterion.Conditions lightningStrike(Consumer<LightningStrikeBuilder> consumer) {
        LightningStrikeBuilder builder = new LightningStrikeBuilder();
        consumer.accept(builder);
        return new LightningStrikeCriterion.Conditions(builder.player, builder.lightning, builder.bystander);
    }

    public PlayerGeneratesContainerLootCriterion.Conditions lootTable(Consumer<LootTableBuilder> consumer) {
        LootTableBuilder builder = new LootTableBuilder();
        consumer.accept(builder);
        return new PlayerGeneratesContainerLootCriterion.Conditions(builder.player, builder.lootTable);
    }

    public PlacedBlockCriterion.Conditions placedBlock(Consumer<PlacedBlockBuilder> consumer) {
        PlacedBlockBuilder builder = new PlacedBlockBuilder();
        consumer.accept(builder);
        return new PlacedBlockCriterion.Conditions(builder.player, builder.block, builder.state, builder.location, builder.item);
    }

    public PlayerHurtEntityCriterion.Conditions playerHurtEntity(Consumer<PlayerHurtEntityBuilder> consumer) {
        PlayerHurtEntityBuilder builder = new PlayerHurtEntityBuilder();
        consumer.accept(builder);
        return new PlayerHurtEntityCriterion.Conditions(builder.player, builder.damage, builder.entity);
    }

    public PlayerInteractedWithEntityCriterion.Conditions playerInteract(Consumer<PlayerInteractBuilder> consumer) {
        PlayerInteractBuilder builder = new PlayerInteractBuilder();
        consumer.accept(builder);
        return new PlayerInteractedWithEntityCriterion.Conditions(builder.player, builder.item, builder.entity);
    }

    public RecipeUnlockedCriterion.Conditions recipeUnlocked(Consumer<RecipeUnlockedBuilder> consumer) {
        RecipeUnlockedBuilder builder = new RecipeUnlockedBuilder();
        consumer.accept(builder);
        return new RecipeUnlockedCriterion.Conditions(builder.player, builder.recipe);
    }

    public ShotCrossbowCriterion.Conditions shotCrossbow(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ShotCrossbowCriterion.Conditions(builder.player, builder.item);
    }

    public SlideDownBlockCriterion.Conditions slideDownBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new SlideDownBlockCriterion.Conditions(builder.player, builder.block, builder.state);
    }

    public StartedRidingCriterion.Conditions startRiding(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new StartedRidingCriterion.Conditions(builder.build());
    }

    public StartedRidingCriterion.Conditions startRiding() {
        return new StartedRidingCriterion.Conditions(EntityPredicate.Extended.EMPTY);
    }

    public SummonedEntityCriterion.Conditions summonedEntity(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new SummonedEntityCriterion.Conditions(builder.player, builder.entity);
    }

    public TameAnimalCriterion.Conditions tameAnimal(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new TameAnimalCriterion.Conditions(builder.player, builder.entity);
    }

    public TargetHitCriterion.Conditions targetBlock(Consumer<TargetBlockBuilder> consumer) {
        TargetBlockBuilder builder = new TargetBlockBuilder();
        consumer.accept(builder);
        return new TargetHitCriterion.Conditions(builder.player, builder.signalStrength, builder.projectile);
    }

    public TickCriterion.Conditions tick(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(builder.build());
    }

    public TickCriterion.Conditions tick() {
        return new TickCriterion.Conditions(EntityPredicate.Extended.EMPTY);
    }

    public VillagerTradeCriterion.Conditions trade(Consumer<TradeBuilder> consumer) {
        TradeBuilder builder = new TradeBuilder();
        consumer.accept(builder);
        return new VillagerTradeCriterion.Conditions(builder.player, builder.villager, builder.item);
    }

    public UsedEnderEyeCriterion.Conditions usedEnderEye(Consumer<UsedEnderEyeBuilder> consumer) {
        UsedEnderEyeBuilder builder = new UsedEnderEyeBuilder();
        consumer.accept(builder);
        return new UsedEnderEyeCriterion.Conditions(builder.player, builder.distance);
    }

    public UsedTotemCriterion.Conditions usedTotem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsedTotemCriterion.Conditions(builder.player, builder.item);
    }

    public UsingItemCriterion.Conditions usingItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsingItemCriterion.Conditions(builder.player, builder.item);
    }
}
