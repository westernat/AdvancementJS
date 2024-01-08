package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancement.criterion.*;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.trigger.custom.BlockDestroyedCriterion;
import org.mesdag.advjs.trigger.custom.BossEventConditions;
import org.mesdag.advjs.trigger.custom.PlayerTouchConditions;

import java.util.function.Consumer;

public class Trigger {
    @Info("Custom trigger, triggers when the player breaks a block.")
    public BlockDestroyedCriterion.Conditions blockDestroyed(Consumer<BlockDestroyedCriterion.Builder> consumer) {
        return BlockDestroyedCriterion.blockDestroyed(consumer);
    }

    @Info("Custom trigger, triggers when the player touch an entity.")
    public PlayerTouchConditions.Conditions playerTouch(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new PlayerTouchConditions.Conditions(builder.player, builder.entity);
    }

    @Info("Custom trigger, triggers when the play joins a boss fight.")
    public BossEventConditions.Conditions bossEvent(Consumer<BossEventConditions.Builder> consumer) {
        return BossEventConditions.bossEvent(consumer);
    }

    @Info("Never triggers.")
    public ImpossibleCriterion.Conditions impossible() {
        return new ImpossibleCriterion.Conditions();
    }

    @Info("Triggers when the player breaks a bee nest or beehive.")
    public BeeNestDestroyedCriterion.Conditions beeNestDestroyed(Consumer<BeeNestDestroyedBuilder> consumer) {
        BeeNestDestroyedBuilder builder = new BeeNestDestroyedBuilder();
        consumer.accept(builder);
        return new BeeNestDestroyedCriterion.Conditions(builder.player, builder.block, builder.item, builder.bounds);
    }

    @Info("Triggers after the player breeds 2 animals.")
    public BredAnimalsCriterion.Conditions bredAnimals(Consumer<BredAnimalsBuilder> consumer) {
        BredAnimalsBuilder builder = new BredAnimalsBuilder();
        consumer.accept(builder);
        return new BredAnimalsCriterion.Conditions(builder.player, builder.parent, builder.partner, builder.child);
    }

    @Info("Triggers after the player takes any item out of a brewing stand.")
    public BrewedPotionCriterion.Conditions brewedPotion(Consumer<BrewedPotionBuilder> consumer) {
        BrewedPotionBuilder builder = new BrewedPotionBuilder();
        consumer.accept(builder);
        return new BrewedPotionCriterion.Conditions(builder.player, builder.potion);
    }

    @Info("Triggers after the player travels between two dimensions.")
    public ChangedDimensionCriterion.Conditions changedDimension(Consumer<ChangeDimensionBuilder> consumer) {
        ChangeDimensionBuilder builder = new ChangeDimensionBuilder();
        consumer.accept(builder);
        return new ChangedDimensionCriterion.Conditions(builder.player, builder.from, builder.to);
    }

    @Info("Triggers after the player successfully uses the Channeling enchantment on an entity or a lightning rod.")
    public ChanneledLightningCriterion.Conditions channeledLightning(Consumer<ChanneledLightningBuilder> consumer) {
        ChanneledLightningBuilder builder = new ChanneledLightningBuilder();
        consumer.accept(builder);
        return new ChanneledLightningCriterion.Conditions(builder.player, builder.victims);
    }

    @Info("Triggers after the player changes the structure of a beacon. (When the beacon updates itself).")
    public ConstructBeaconCriterion.Conditions constructedBeacon(Consumer<ConstructBeaconBuilder> consumer) {
        ConstructBeaconBuilder builder = new ConstructBeaconBuilder();
        consumer.accept(builder);
        return new ConstructBeaconCriterion.Conditions(builder.player, builder.bounds);
    }

    @Info("Triggers when the player cures a zombie villager.")
    public CuredZombieVillagerCriterion.Conditions curedZombieVillager(Consumer<CuredZombieVillagerBuilder> consumer) {
        CuredZombieVillagerBuilder builder = new CuredZombieVillagerBuilder();
        consumer.accept(builder);
        return new CuredZombieVillagerCriterion.Conditions(builder.player, builder.zombie, builder.villager);
    }

    @Info(value = "Triggers when the player consumes an item.",
        params = @Param(name = "The item that was consumed.", value = "The item that was consumed."))
    public ConsumeItemCriterion.Conditions consumeItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ConsumeItemCriterion.Conditions(builder.player, builder.item);
    }

    @Info("Triggers when a player lands after falling.")
    public TravelCriterion.Conditions fallFromHeight(Consumer<FallFromHeightBuilder> consumer) {
        FallFromHeightBuilder builder = new FallFromHeightBuilder();
        consumer.accept(builder);
        return new TravelCriterion.Conditions(Criteria.FALL_FROM_HEIGHT.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers when a player mounts an entity walking on lava and while the entity moves with them.")
    public TravelCriterion.Conditions rideEntityInLava(Consumer<RideEntityInLavaBuilder> consumer) {
        RideEntityInLavaBuilder builder = new RideEntityInLavaBuilder();
        consumer.accept(builder);
        return new TravelCriterion.Conditions(Criteria.RIDE_ENTITY_IN_LAVA.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers when the player travels to the Nether and then returns to the Overworld.")
    public TravelCriterion.Conditions travelledThroughNether(Consumer<TraveledThroughNetherBuilder> consumer) {
        TraveledThroughNetherBuilder builder = new TraveledThroughNetherBuilder();
        consumer.accept(builder);
        return new TravelCriterion.Conditions(Criteria.NETHER_TRAVEL.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers after the player gets a status effect applied or taken from them.")
    public EffectsChangedCriterion.Conditions effectChanged(Consumer<EffectsChangedBuilder> consumer) {
        EffectsChangedBuilder builder = new EffectsChangedBuilder();
        consumer.accept(builder);
        return new EffectsChangedCriterion.Conditions(builder.player, builder.effects, builder.source);
    }

    @Info("""
        Triggers after the player enchants an item through an enchanting table
                
        (does not get triggered through an anvil, or through commands).
        """)
    public EnchantedItemCriterion.Conditions enchantedItem(Consumer<EnchantedItemBuilder> consumer) {
        EnchantedItemBuilder builder = new EnchantedItemBuilder();
        consumer.accept(builder);
        return new EnchantedItemCriterion.Conditions(builder.player, builder.item, builder.levels);
    }

    @Info("""
        Triggers once for each block the player's hitbox is inside (up to 12 blocks, the maximum number of blocks the player can stand in),
                
        twice per tick plus once more for every time the player moves or looks around during the same tick.
                
        This results in the trigger activating tens of times per tick, and in extreme cases, even hundreds of times per tick.
                
        @param blockId The block that the player is standing in.
                
        @param state A map of block property names to values. Errors if the block doesn't have these properties.
        """)
    public EnterBlockCriterion.Conditions entersBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new EnterBlockCriterion.Conditions(builder.player, builder.block, builder.state);
    }

    @Info("Triggers after a player gets hurt (even when it's not caused by an entity).")
    public EntityHurtPlayerCriterion.Conditions entityHurtPlayer(Consumer<EntityHurtPlayerBuilder> consumer) {
        EntityHurtPlayerBuilder builder = new EntityHurtPlayerBuilder();
        consumer.accept(builder);
        return new EntityHurtPlayerCriterion.Conditions(builder.player, builder.damage);
    }

    @Info("""
        Triggers after the player fills a bucket.
                
        @param item The item resulting from filling the bucket.
        """)
    public FilledBucketCriterion.Conditions filledBucket(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new FilledBucketCriterion.Conditions(builder.player, builder.item);
    }

    @Info("Triggers after the player successfully catches an item with a fishing rod or pulls an entity with a fishing rod.")
    public FishingRodHookedCriterion.Conditions fishingRodHooked(Consumer<FishingRodHookedBuilder> consumer) {
        FishingRodHookedBuilder builder = new FishingRodHookedBuilder();
        consumer.accept(builder);
        return new FishingRodHookedCriterion.Conditions(builder.player, builder.rod, builder.entity, builder.item);
    }

    @Info("Triggers after any changes happen to the player's inventory.")
    public InventoryChangedCriterion.Conditions inventoryChange(Consumer<InventoryChangeBuilder> consumer) {
        InventoryChangeBuilder builder = new InventoryChangeBuilder();
        consumer.accept(builder);
        return new InventoryChangedCriterion.Conditions(builder.player, builder.slotsOccupied, builder.slotsFull, builder.slotsEmpty, builder.items);
    }

    @Info("Triggers after any item in the inventory has been damaged in any form.")
    public ItemDurabilityChangedCriterion.Conditions itemDurability(Consumer<ItemDurabilityBuilder> consumer) {
        ItemDurabilityBuilder builder = new ItemDurabilityBuilder();
        consumer.accept(builder);
        return new ItemDurabilityChangedCriterion.Conditions(builder.player, builder.item, builder.durability, builder.delta);
    }

    @Info("Triggers after the player throws an item and another entity picks it up.")
    public ThrownItemPickedUpByEntityCriterion.Conditions itemPickedUpByEntity(Consumer<ItemPickedUpByEntityBuilder> consumer) {
        ItemPickedUpByEntityBuilder builder = new ItemPickedUpByEntityBuilder();
        consumer.accept(builder);
        return new ThrownItemPickedUpByEntityCriterion.Conditions(Criteria.THROWN_ITEM_PICKED_UP_BY_ENTITY.getId(), builder.player, builder.item, builder.entity);
    }

    @Info("Triggers when a player picks up an item thrown by another entity.")
    public ThrownItemPickedUpByEntityCriterion.Conditions itemPickedUpByPlayer(Consumer<ItemPickedUpByPlayerBuilder> consumer) {
        ItemPickedUpByPlayerBuilder builder = new ItemPickedUpByPlayerBuilder();
        consumer.accept(builder);
        return new ThrownItemPickedUpByEntityCriterion.Conditions(Criteria.THROWN_ITEM_PICKED_UP_BY_PLAYER.getId(), builder.player, builder.item, builder.entity);
    }

    // TODO ItemCriterion

    @Info("Triggers after the player kills a mob or player using a crossbow in ranged combat.")
    public KilledByCrossbowCriterion.Conditions killedByCrossbow(Consumer<KilledByCrossbowBuilder> consumer) {
        KilledByCrossbowBuilder builder = new KilledByCrossbowBuilder();
        consumer.accept(builder);
        return new KilledByCrossbowCriterion.Conditions(builder.player, builder.victims, builder.uniqueEntityTypes);
    }

    @Info("Triggers after a player is the source of a mob or player being killed.")
    public OnKilledCriterion.Conditions playerKilledEntity(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new OnKilledCriterion.Conditions(Criteria.PLAYER_KILLED_ENTITY.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    @Info("Triggers after a living entity kills a player.")
    public OnKilledCriterion.Conditions entityKilledPlayer(Consumer<EntityKillPlayerBuilder> consumer) {
        EntityKillPlayerBuilder builder = new EntityKillPlayerBuilder();
        consumer.accept(builder);
        return new OnKilledCriterion.Conditions(Criteria.ENTITY_KILLED_PLAYER.getId(), builder.player, builder.killer, builder.killingBlow);
    }

    @Info("Triggers after a player is the source of a mob or player being killed within the range of a sculk catalyst.")
    public OnKilledCriterion.Conditions playerKilledEntityNearSculkCatalyst(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new OnKilledCriterion.Conditions(Criteria.KILL_MOB_NEAR_SCULK_CATALYST.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    @Info("Triggers when the player has the levitation status effect.")
    public LevitationCriterion.Conditions levitated(Consumer<LevitationBuilder> consumer) {
        LevitationBuilder builder = new LevitationBuilder();
        consumer.accept(builder);
        return new LevitationCriterion.Conditions(builder.player, builder.distance, builder.duration);
    }

    @Info("Triggers when a lightning bolt disappears from the world, only for players within a 256 block radius of the lightning bolt.")
    public LightningStrikeCriterion.Conditions lightningStrike(Consumer<LightningStrikeBuilder> consumer) {
        LightningStrikeBuilder builder = new LightningStrikeBuilder();
        consumer.accept(builder);
        return new LightningStrikeCriterion.Conditions(builder.player, builder.lightning, builder.bystander);
    }

    @Info("Triggers when the player generates the contents of a container with a loot table set.")
    public PlayerGeneratesContainerLootCriterion.Conditions lootTable(Consumer<LootTableBuilder> consumer) {
        LootTableBuilder builder = new LootTableBuilder();
        consumer.accept(builder);
        return new PlayerGeneratesContainerLootCriterion.Conditions(builder.player, builder.lootTable);
    }

    @Info("Triggers after the player hurts a mob or player.")
    public PlayerHurtEntityCriterion.Conditions playerHurtEntity(Consumer<PlayerHurtEntityBuilder> consumer) {
        PlayerHurtEntityBuilder builder = new PlayerHurtEntityBuilder();
        consumer.accept(builder);
        return new PlayerHurtEntityCriterion.Conditions(builder.player, builder.damage, builder.entity);
    }

    @Info("Triggers when the player interacts with an entity.")
    public PlayerInteractedWithEntityCriterion.Conditions playerInteract(Consumer<PlayerInteractBuilder> consumer) {
        PlayerInteractBuilder builder = new PlayerInteractBuilder();
        consumer.accept(builder);
        return new PlayerInteractedWithEntityCriterion.Conditions(builder.player, builder.item, builder.entity);
    }

    @Info("Triggers after the player unlocks a recipe (using a knowledge book for example).")
    public RecipeUnlockedCriterion.Conditions recipeUnlocked(Consumer<RecipeUnlockedBuilder> consumer) {
        RecipeUnlockedBuilder builder = new RecipeUnlockedBuilder();
        consumer.accept(builder);
        return new RecipeUnlockedCriterion.Conditions(builder.player, builder.recipe);
    }

    @Info(value = "Triggers when the player shoots a crossbow.",
        params = @Param(name = "item", value = "The crossbow that is used."))
    public ShotCrossbowCriterion.Conditions shotCrossbow(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ShotCrossbowCriterion.Conditions(builder.player, builder.item);
    }

    @Info(value = "Triggers when the player slides down a block.",
        params = {
            @Param(name = "blockId", value = "The block that the player slid on."),
            @Param(name = "state", value = "A map of block property names to values. Errors if the block doesn't have these properties.")
        })
    public SlideDownBlockCriterion.Conditions slideDownBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new SlideDownBlockCriterion.Conditions(builder.player, builder.block, builder.state);
    }

    @Info("Triggers when the player starts riding a vehicle or an entity starts riding a vehicle currently ridden by the player.")
    public StartedRidingCriterion.Conditions startRiding(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new StartedRidingCriterion.Conditions(builder.build());
    }

    @Info("Triggers when the player starts riding a vehicle or an entity starts riding a vehicle currently ridden by the player.")
    public StartedRidingCriterion.Conditions startRiding() {
        return new StartedRidingCriterion.Conditions(LootContextPredicate.EMPTY);
    }

    @Info("""
        Triggers after an entity has been summoned.
                
        Works with iron golem, snow golem, the ender dragon and the wither.
                
        Using dispensers, commands, or pistons to place the wither skulls or pumpkins will still activate this trigger.
                
        @param entity The summoned entity.
        """)
    public SummonedEntityCriterion.Conditions summonedEntity(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new SummonedEntityCriterion.Conditions(builder.player, builder.entity);
    }

    @Info("""
        Triggers after the player tames an animal.
                
        @param entity Checks the entity that was tamed.
        """)
    public TameAnimalCriterion.Conditions tameAnimal(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new TameAnimalCriterion.Conditions(builder.player, builder.entity);
    }

    @Info("Triggers when the player shoots a target block.")
    public TargetHitCriterion.Conditions targetBlock(Consumer<TargetHitBuilder> consumer) {
        TargetHitBuilder builder = new TargetHitBuilder();
        consumer.accept(builder);
        return new TargetHitCriterion.Conditions(builder.player, builder.signalStrength, builder.projectile);
    }

    @Info("Triggers every tick (20 times a second).")
    public TickCriterion.Conditions tick(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.TICK.getId(), builder.build());
    }

    @Info("Triggers every tick (20 times a second).")
    public TickCriterion.Conditions tick() {
        return new TickCriterion.Conditions(Criteria.TICK.getId(), LootContextPredicate.EMPTY);
    }

    @Info("Triggers every 20 ticks (1 second).")
    public TickCriterion.Conditions location(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.LOCATION.getId(), builder.build());
    }

    @Info("Triggers when the player enters a bed.")
    public TickCriterion.Conditions sleptInBed(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.SLEPT_IN_BED.getId(), builder.build());
    }

    @Info("Triggers when the player enters a bed.")
    public TickCriterion.Conditions sleptInBed() {
        return new TickCriterion.Conditions(Criteria.SLEPT_IN_BED.getId(), LootContextPredicate.EMPTY);
    }

    @Info("Triggers when a raid ends in victory and the player has attacked at least one raider from that raid.")
    public TickCriterion.Conditions raidWon(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.HERO_OF_THE_VILLAGE.getId(), builder.build());
    }

    @Info("Triggers when a raid ends in victory and the player has attacked at least one raider from that raid.")
    public TickCriterion.Conditions raidWon() {
        return new TickCriterion.Conditions(Criteria.HERO_OF_THE_VILLAGE.getId(), LootContextPredicate.EMPTY);
    }

    @Info("Triggers when the player causes a raid.")
    public TickCriterion.Conditions badOmen(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.VOLUNTARY_EXILE.getId(), builder.build());
    }

    @Info("Triggers when the player causes a raid.")
    public TickCriterion.Conditions badOmen() {
        return new TickCriterion.Conditions(Criteria.VOLUNTARY_EXILE.getId(), LootContextPredicate.EMPTY);
    }

    @Info("Triggers when a vibration event is ignored because the source player is crouching.")
    public TickCriterion.Conditions avoidVibration(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickCriterion.Conditions(Criteria.AVOID_VIBRATION.getId(), builder.build());
    }

    @Info("Triggers when a vibration event is ignored because the source player is crouching.")
    public TickCriterion.Conditions avoidVibration() {
        return new TickCriterion.Conditions(Criteria.AVOID_VIBRATION.getId(), LootContextPredicate.EMPTY);
    }

    @Info("Triggers after the player trades with a villager or a wandering trader.")
    public VillagerTradeCriterion.Conditions villagerTrade(Consumer<TradeBuilder> consumer) {
        TradeBuilder builder = new TradeBuilder();
        consumer.accept(builder);
        return new VillagerTradeCriterion.Conditions(builder.player, builder.villager, builder.item);
    }

    @Info("Triggers when the player uses an eye of ender (in a world where strongholds generate).")
    public UsedEnderEyeCriterion.Conditions usedEnderEye(Consumer<UsedEnderEyeBuilder> consumer) {
        UsedEnderEyeBuilder builder = new UsedEnderEyeBuilder();
        consumer.accept(builder);
        return new UsedEnderEyeCriterion.Conditions(builder.player, builder.distance);
    }

    @Info("""
        Triggers when the player uses a totem.
                
        @param item The item, only works with totem items.
        """)
    public UsedTotemCriterion.Conditions usedTotem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsedTotemCriterion.Conditions(builder.player, builder.item);
    }

    @Info("""
        Triggers when the player uses a totem.
                
        @param item The item, only works with totem items.
        """)
    public UsingItemCriterion.Conditions usingItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsingItemCriterion.Conditions(builder.player, builder.item);
    }
}
