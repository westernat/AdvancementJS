package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import org.mesdag.advjs.predicate.PlayerPredicateBuilder;
import org.mesdag.advjs.trigger.custom.BlockDestroyedTrigger;
import org.mesdag.advjs.trigger.custom.BossEventTrigger;
import org.mesdag.advjs.trigger.custom.PlayerTouchTrigger;
import org.mesdag.advjs.util.ItemSetter;

import java.util.List;
import java.util.function.Consumer;

public class Trigger implements ItemSetter {
    @Info("Custom trigger, triggers when the player breaks a block.")
    public BlockDestroyedTrigger.TriggerInstance blockDestroyed(Consumer<BlockDestroyedTrigger.Builder> consumer) {
        return BlockDestroyedTrigger.blockDestroyed(consumer);
    }

    @Info("Custom trigger, triggers when the player touch an entity.")
    public PlayerTouchTrigger.TriggerInstance playerTouch(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new PlayerTouchTrigger.TriggerInstance(builder.player, builder.entity);
    }

    @Info("Custom trigger, triggers when the play joins a boss fight.")
    public BossEventTrigger.TriggerInstance bossEvent(Consumer<BossEventTrigger.Builder> consumer) {
        return BossEventTrigger.bossEvent(consumer);
    }

    @Info("Never triggers.")
    public ImpossibleTrigger.TriggerInstance impossible() {
        return new ImpossibleTrigger.TriggerInstance();
    }

    @Info("Triggers when the player breaks a bee nest or beehive.")
    public BeeNestDestroyedTrigger.TriggerInstance beeNestDestroyed(Consumer<BeeNestDestroyedBuilder> consumer) {
        BeeNestDestroyedBuilder builder = new BeeNestDestroyedBuilder();
        consumer.accept(builder);
        return new BeeNestDestroyedTrigger.TriggerInstance(builder.player, builder.block, builder.item, builder.bounds);
    }

    @Info("Triggers after the player breeds 2 animals.")
    public BredAnimalsTrigger.TriggerInstance bredAnimals(Consumer<BredAnimalsBuilder> consumer) {
        BredAnimalsBuilder builder = new BredAnimalsBuilder();
        consumer.accept(builder);
        return new BredAnimalsTrigger.TriggerInstance(builder.player, builder.parent, builder.partner, builder.child);
    }

    @Info("Triggers after the player takes any item out of a brewing stand.")
    public BrewedPotionTrigger.TriggerInstance brewedPotion(Consumer<BrewedPotionBuilder> consumer) {
        BrewedPotionBuilder builder = new BrewedPotionBuilder();
        consumer.accept(builder);
        return new BrewedPotionTrigger.TriggerInstance(builder.player, builder.potion);
    }

    @Info("Triggers after the player travels between two dimensions.")
    public ChangeDimensionTrigger.TriggerInstance changedDimension(Consumer<ChangeDimensionBuilder> consumer) {
        ChangeDimensionBuilder builder = new ChangeDimensionBuilder();
        consumer.accept(builder);
        return new ChangeDimensionTrigger.TriggerInstance(builder.player, builder.from, builder.to);
    }

    @Info("Triggers after the player successfully uses the Channeling enchantment on an entity or a lightning rod.")
    public ChanneledLightningTrigger.TriggerInstance channeledLightning(Consumer<ChanneledLightningBuilder> consumer) {
        ChanneledLightningBuilder builder = new ChanneledLightningBuilder();
        consumer.accept(builder);
        return new ChanneledLightningTrigger.TriggerInstance(builder.player, builder.victims);
    }

    @Info("Triggers after the player changes the structure of a beacon. (When the beacon updates itself).")
    public ConstructBeaconTrigger.TriggerInstance constructedBeacon(Consumer<ConstructBeaconBuilder> consumer) {
        ConstructBeaconBuilder builder = new ConstructBeaconBuilder();
        consumer.accept(builder);
        return new ConstructBeaconTrigger.TriggerInstance(builder.player, builder.level);
    }

    @Info("Triggers when the player cures a zombie villager.")
    public CuredZombieVillagerTrigger.TriggerInstance curedZombieVillager(Consumer<CuredZombieVillagerBuilder> consumer) {
        CuredZombieVillagerBuilder builder = new CuredZombieVillagerBuilder();
        consumer.accept(builder);
        return new CuredZombieVillagerTrigger.TriggerInstance(builder.player, builder.zombie, builder.villager);
    }

    @Info(value = "Triggers when the player consumes an item.",
        params = @Param(name = "The item that was consumed.", value = "The item that was consumed."))
    public ConsumeItemTrigger.TriggerInstance consumeItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ConsumeItemTrigger.TriggerInstance(builder.player, builder.item);
    }

    @Info("Triggers when a player lands after falling.")
    public DistanceTrigger.TriggerInstance fallFromHeight(Consumer<FallFromHeightBuilder> consumer) {
        FallFromHeightBuilder builder = new FallFromHeightBuilder();
        consumer.accept(builder);
        return new DistanceTrigger.TriggerInstance(CriteriaTriggers.FALL_FROM_HEIGHT.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers when a player mounts an entity walking on lava and while the entity moves with them.")
    public DistanceTrigger.TriggerInstance rideEntityInLava(Consumer<RideEntityInLavaBuilder> consumer) {
        RideEntityInLavaBuilder builder = new RideEntityInLavaBuilder();
        consumer.accept(builder);
        return new DistanceTrigger.TriggerInstance(CriteriaTriggers.RIDE_ENTITY_IN_LAVA_TRIGGER.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers when the player travels to the Nether and then returns to the Overworld.")
    public DistanceTrigger.TriggerInstance travelledThroughNether(Consumer<TraveledThroughNetherBuilder> consumer) {
        TraveledThroughNetherBuilder builder = new TraveledThroughNetherBuilder();
        consumer.accept(builder);
        return new DistanceTrigger.TriggerInstance(CriteriaTriggers.NETHER_TRAVEL.getId(), builder.player, builder.startPosition, builder.distance);
    }

    @Info("Triggers after the player gets a status effect applied or taken from them.")
    public EffectsChangedTrigger.TriggerInstance effectChanged(Consumer<EffectsChangedBuilder> consumer) {
        EffectsChangedBuilder builder = new EffectsChangedBuilder();
        consumer.accept(builder);
        return new EffectsChangedTrigger.TriggerInstance(builder.player, builder.effects, builder.source);
    }

    @Info("""
        Triggers after the player enchants an item through an enchanting table
                
        (does not get triggered through an anvil, or through commands).
        """)
    public EnchantedItemTrigger.TriggerInstance enchantedItem(Consumer<EnchantedItemBuilder> consumer) {
        EnchantedItemBuilder builder = new EnchantedItemBuilder();
        consumer.accept(builder);
        return new EnchantedItemTrigger.TriggerInstance(builder.player, builder.item, builder.levels);
    }

    @Info("""
        Triggers once for each block the player's hitbox is inside (up to 12 blocks, the maximum number of blocks the player can stand in),
                
        twice per tick plus once more for every time the player moves or looks around during the same tick.
                
        This results in the trigger activating tens of times per tick, and in extreme cases, even hundreds of times per tick.
                
        @param blockId The block that the player is standing in.
                
        @param state A map of block property names to values. Errors if the block doesn't have these properties.
        """)
    public EnterBlockTrigger.TriggerInstance entersBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new EnterBlockTrigger.TriggerInstance(builder.player, builder.block, builder.state);
    }

    @Info("Triggers after a player gets hurt (even when it's not caused by an entity).")
    public EntityHurtPlayerTrigger.TriggerInstance entityHurtPlayer(Consumer<EntityHurtPlayerBuilder> consumer) {
        EntityHurtPlayerBuilder builder = new EntityHurtPlayerBuilder();
        consumer.accept(builder);
        return new EntityHurtPlayerTrigger.TriggerInstance(builder.player, builder.damage);
    }

    @Info("""
        Triggers after the player fills a bucket.
                
        @param item The item resulting from filling the bucket.
        """)
    public FilledBucketTrigger.TriggerInstance filledBucket(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new FilledBucketTrigger.TriggerInstance(builder.player, builder.item);
    }

    @Info("Triggers after the player successfully catches an item with a fishing rod or pulls an entity with a fishing rod.")
    public FishingRodHookedTrigger.TriggerInstance fishingRodHooked(Consumer<FishingRodHookedBuilder> consumer) {
        FishingRodHookedBuilder builder = new FishingRodHookedBuilder();
        consumer.accept(builder);
        return new FishingRodHookedTrigger.TriggerInstance(builder.player, builder.rod, builder.entity, builder.item);
    }

    @Info("Triggers after any changes happen to the player's inventory.")
    public InventoryChangeTrigger.TriggerInstance inventoryChange(Consumer<InventoryChangeBuilder> consumer) {
        InventoryChangeBuilder builder = new InventoryChangeBuilder();
        consumer.accept(builder);
        return new InventoryChangeTrigger.TriggerInstance(builder.player, builder.slotsOccupied, builder.slotsFull, builder.slotsEmpty, builder.items);
    }

    @Info("Triggers after any changes happen to the player's inventory.")
    public InventoryChangeTrigger.TriggerInstance hasItems(ItemPredicate... itemPredicates) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(itemPredicates);
    }

    @Info("Triggers after any changes happen to the player's inventory.")
    public InventoryChangeTrigger.TriggerInstance hasItems(Ingredient ingredient) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(wrapItem(ingredient));
    }

    @Info("Triggers after any item in the inventory has been damaged in any form.")
    public ItemDurabilityTrigger.TriggerInstance itemDurability(Consumer<ItemDurabilityBuilder> consumer) {
        ItemDurabilityBuilder builder = new ItemDurabilityBuilder();
        consumer.accept(builder);
        return new ItemDurabilityTrigger.TriggerInstance(builder.player, builder.item, builder.durability, builder.delta);
    }

    @Info("Triggers after the player throws an item and another entity picks it up.")
    public PickedUpItemTrigger.TriggerInstance itemPickedUpByEntity(Consumer<ItemPickedUpByEntityBuilder> consumer) {
        ItemPickedUpByEntityBuilder builder = new ItemPickedUpByEntityBuilder();
        consumer.accept(builder);
        return new PickedUpItemTrigger.TriggerInstance(CriteriaTriggers.THROWN_ITEM_PICKED_UP_BY_ENTITY.getId(), builder.player, builder.item, builder.entity);
    }

    @Info("Triggers when a player picks up an item thrown by another entity.")
    public PickedUpItemTrigger.TriggerInstance itemPickedUpByPlayer(Consumer<ItemPickedUpByPlayerBuilder> consumer) {
        ItemPickedUpByPlayerBuilder builder = new ItemPickedUpByPlayerBuilder();
        consumer.accept(builder);
        return new PickedUpItemTrigger.TriggerInstance(CriteriaTriggers.THROWN_ITEM_PICKED_UP_BY_PLAYER.getId(), builder.player, builder.item, builder.entity);
    }

    @Info("Triggers when the player places a block.")
    public ItemUsedOnLocationTrigger.TriggerInstance placedBlock(Consumer<ItemUsedOnLocationBuilder> consumer) {
        ItemUsedOnLocationBuilder builder = new ItemUsedOnLocationBuilder();
        consumer.accept(builder);
        return new ItemUsedOnLocationTrigger.TriggerInstance(CriteriaTriggers.PLACED_BLOCK.getId(), builder.player, builder.createContext());
    }

    @Info("Triggers when the player uses their hand or an item on a block.")
    public ItemUsedOnLocationTrigger.TriggerInstance itemUsedOnBlock(Consumer<ItemUsedOnLocationBuilder> consumer) {
        ItemUsedOnLocationBuilder builder = new ItemUsedOnLocationBuilder();
        consumer.accept(builder);
        return new ItemUsedOnLocationTrigger.TriggerInstance(CriteriaTriggers.ITEM_USED_ON_BLOCK.getId(), builder.player, builder.createContext());
    }

    @Info("Triggers when an allay drops an item on a block.")
    public ItemUsedOnLocationTrigger.TriggerInstance allayDropItemOnBlock(Consumer<ItemUsedOnLocationBuilder> consumer) {
        ItemUsedOnLocationBuilder builder = new ItemUsedOnLocationBuilder();
        consumer.accept(builder);
        return new ItemUsedOnLocationTrigger.TriggerInstance(CriteriaTriggers.ALLAY_DROP_ITEM_ON_BLOCK.getId(), builder.player, builder.createContext());
    }

    @Info("Triggers after the player kills a mob or player using a crossbow in ranged combat.")
    public KilledByCrossbowTrigger.TriggerInstance killedByCrossbow(Consumer<KilledByCrossbowBuilder> consumer) {
        KilledByCrossbowBuilder builder = new KilledByCrossbowBuilder();
        consumer.accept(builder);
        return new KilledByCrossbowTrigger.TriggerInstance(builder.player, builder.victims, builder.uniqueEntityTypes);
    }

    @Info("Triggers after a player is the source of a mob or player being killed.")
    public KilledTrigger.TriggerInstance playerKilledEntity(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new KilledTrigger.TriggerInstance(CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    @Info("Triggers after a living entity kills a player.")
    public KilledTrigger.TriggerInstance entityKilledPlayer(Consumer<EntityKillPlayerBuilder> consumer) {
        EntityKillPlayerBuilder builder = new EntityKillPlayerBuilder();
        consumer.accept(builder);
        return new KilledTrigger.TriggerInstance(CriteriaTriggers.ENTITY_KILLED_PLAYER.getId(), builder.player, builder.killer, builder.killingBlow);
    }

    @Info("Triggers after a player is the source of a mob or player being killed within the range of a sculk catalyst.")
    public KilledTrigger.TriggerInstance playerKilledEntityNearSculkCatalyst(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new KilledTrigger.TriggerInstance(CriteriaTriggers.KILL_MOB_NEAR_SCULK_CATALYST.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    @Info("Triggers when the player has the levitation status effect.")
    public LevitationTrigger.TriggerInstance levitated(Consumer<LevitationBuilder> consumer) {
        LevitationBuilder builder = new LevitationBuilder();
        consumer.accept(builder);
        return new LevitationTrigger.TriggerInstance(builder.player, builder.distance, builder.duration);
    }

    @Info("Triggers when a lightning bolt disappears from the world, only for players within a 256 block radius of the lightning bolt.")
    public LightningStrikeTrigger.TriggerInstance lightningStrike(Consumer<LightningStrikeBuilder> consumer) {
        LightningStrikeBuilder builder = new LightningStrikeBuilder();
        consumer.accept(builder);
        return new LightningStrikeTrigger.TriggerInstance(builder.player, builder.lightning, builder.bystander);
    }

    @Info("Triggers when the player generates the contents of a container with a loot table set.")
    public LootTableTrigger.TriggerInstance lootTable(Consumer<LootTableBuilder> consumer) {
        LootTableBuilder builder = new LootTableBuilder();
        consumer.accept(builder);
        return new LootTableTrigger.TriggerInstance(builder.player, builder.lootTable);
    }

    @Info("Triggers when the player generates the contents of a container with a loot table set.")
    public LootTableTrigger.TriggerInstance lootTableUsed(ResourceLocation id) {
        return LootTableTrigger.TriggerInstance.lootTableUsed(id);
    }

    @Info("Triggers after the player hurts a mob or player.")
    public PlayerHurtEntityTrigger.TriggerInstance playerHurtEntity(Consumer<PlayerHurtEntityBuilder> consumer) {
        PlayerHurtEntityBuilder builder = new PlayerHurtEntityBuilder();
        consumer.accept(builder);
        return new PlayerHurtEntityTrigger.TriggerInstance(builder.player, builder.damage, builder.entity);
    }

    @Info("Triggers when the player interacts with an entity.")
    public PlayerInteractTrigger.TriggerInstance playerInteract(Consumer<PlayerInteractBuilder> consumer) {
        PlayerInteractBuilder builder = new PlayerInteractBuilder();
        consumer.accept(builder);
        return new PlayerInteractTrigger.TriggerInstance(builder.player, builder.item, builder.entity);
    }

    @Info("Triggers after the player unlocks a recipe (using a knowledge book for example).")
    public RecipeUnlockedTrigger.TriggerInstance recipeUnlocked(Consumer<RecipeUnlockedBuilder> consumer) {
        RecipeUnlockedBuilder builder = new RecipeUnlockedBuilder();
        consumer.accept(builder);
        return new RecipeUnlockedTrigger.TriggerInstance(builder.player, builder.recipe);
    }

    //TODO info
    public RecipeCraftedTrigger.TriggerInstance recipeCrafted(Consumer<RecipeCraftedBuilder> consumer) {
        RecipeCraftedBuilder builder = new RecipeCraftedBuilder();
        consumer.accept(builder);
        return new RecipeCraftedTrigger.TriggerInstance(builder.player, builder.recipe, builder.predicates);
    }

    public static RecipeCraftedTrigger.TriggerInstance craftedItem(ResourceLocation recipe, List<ItemPredicate> itemPredicates) {
        return new RecipeCraftedTrigger.TriggerInstance(ContextAwarePredicate.ANY, recipe, itemPredicates);
    }

    public static RecipeCraftedTrigger.TriggerInstance craftedItem(ResourceLocation recipe) {
        return new RecipeCraftedTrigger.TriggerInstance(ContextAwarePredicate.ANY, recipe, List.of());
    }

    @Info(value = "Triggers when the player shoots a crossbow.",
        params = @Param(name = "item", value = "The crossbow that is used."))
    public ShotCrossbowTrigger.TriggerInstance shotCrossbow(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ShotCrossbowTrigger.TriggerInstance(builder.player, builder.item);
    }

    @Info(value = "Triggers when the player slides down a block.",
        params = {
            @Param(name = "blockId", value = "The block that the player slid on."),
            @Param(name = "state", value = "A map of block property names to values. Errors if the block doesn't have these properties.")
        })
    public SlideDownBlockTrigger.TriggerInstance slideDownBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new SlideDownBlockTrigger.TriggerInstance(builder.player, builder.block, builder.state);
    }

    @Info("Triggers when the player starts riding a vehicle or an entity starts riding a vehicle currently ridden by the player.")
    public StartRidingTrigger.TriggerInstance startRiding(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new StartRidingTrigger.TriggerInstance(builder.build());
    }

    @Info("Triggers when the player starts riding a vehicle or an entity starts riding a vehicle currently ridden by the player.")
    public StartRidingTrigger.TriggerInstance startRiding() {
        return new StartRidingTrigger.TriggerInstance(ContextAwarePredicate.ANY);
    }

    @Info("""
        Triggers after an entity has been summoned.
                
        Works with iron golem, snow golem, the ender dragon and the wither.
                
        Using dispensers, commands, or pistons to place the wither skulls or pumpkins will still activate this trigger.
                
        @param entity The summoned entity.
        """)
    public SummonedEntityTrigger.TriggerInstance summonedEntity(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new SummonedEntityTrigger.TriggerInstance(builder.player, builder.entity);
    }

    @Info("""
        Triggers after the player tames an animal.
                
        @param entity Checks the entity that was tamed.
        """)
    public TameAnimalTrigger.TriggerInstance tameAnimal(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new TameAnimalTrigger.TriggerInstance(builder.player, builder.entity);
    }

    @Info("Triggers when the player shoots a target block.")
    public TargetBlockTrigger.TriggerInstance targetHit(Consumer<TargetHitBuilder> consumer) {
        TargetHitBuilder builder = new TargetHitBuilder();
        consumer.accept(builder);
        return new TargetBlockTrigger.TriggerInstance(builder.player, builder.signalStrength, builder.projectile);
    }

    @Info("Triggers every tick (20 times a second).")
    public PlayerTrigger.TriggerInstance tick(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.TICK.getId(), builder.build());
    }

    @Info("Triggers every tick (20 times a second).")
    public PlayerTrigger.TriggerInstance tick() {
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.TICK.getId(), ContextAwarePredicate.ANY);
    }

    @Info("Triggers every 20 ticks (1 second).")
    public PlayerTrigger.TriggerInstance location(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.LOCATION.getId(), builder.build());
    }

    @Info("Triggers when the player enters a bed.")
    public PlayerTrigger.TriggerInstance sleptInBed(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.SLEPT_IN_BED.getId(), builder.build());
    }

    @Info("Triggers when a raid ends in victory and the player has attacked at least one raider from that raid.")
    public PlayerTrigger.TriggerInstance raidWon(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.RAID_WIN.getId(), builder.build());
    }

    @Info("Triggers when the player causes a raid.")
    public PlayerTrigger.TriggerInstance badOmen(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.BAD_OMEN.getId(), builder.build());
    }

    @Info("Triggers when a vibration event is ignored because the source player is crouching.")
    public PlayerTrigger.TriggerInstance avoidVibration(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new PlayerTrigger.TriggerInstance(CriteriaTriggers.AVOID_VIBRATION.getId(), builder.build());
    }

    @Info("Triggers after the player trades with a villager or a wandering trader.")
    public TradeTrigger.TriggerInstance villagerTrade(Consumer<TradeBuilder> consumer) {
        TradeBuilder builder = new TradeBuilder();
        consumer.accept(builder);
        return new TradeTrigger.TriggerInstance(builder.player, builder.villager, builder.item);
    }

    @Info("Triggers when the player uses an eye of ender (in a world where strongholds generate).")
    public UsedEnderEyeTrigger.TriggerInstance usedEnderEye(Consumer<UsedEnderEyeBuilder> consumer) {
        UsedEnderEyeBuilder builder = new UsedEnderEyeBuilder();
        consumer.accept(builder);
        return new UsedEnderEyeTrigger.TriggerInstance(builder.player, builder.distance);
    }

    @Info("""
        Triggers when the player uses a totem.
                
        @param item The item, only works with totem items.
        """)
    public UsedTotemTrigger.TriggerInstance usedTotem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsedTotemTrigger.TriggerInstance(builder.player, builder.item);
    }

    @Info("""
        Triggers when the player uses a totem.
                
        @param item The item, only works with totem items.
        """)
    public UsingItemTrigger.TriggerInstance usingItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsingItemTrigger.TriggerInstance(builder.player, builder.item);
    }
}
