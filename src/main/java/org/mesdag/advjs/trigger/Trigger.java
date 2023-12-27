package org.mesdag.advjs.trigger;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.*;

import java.util.function.Consumer;

public class Trigger {
    public BeeNestDestroyedTrigger.TriggerInstance destroyBlock(Consumer<DestroyBlockBuilder> consumer) {
        DestroyBlockBuilder builder = new DestroyBlockBuilder();
        consumer.accept(builder);
        return new BeeNestDestroyedTrigger.TriggerInstance(builder.player, builder.block, builder.item, builder.bounds);
    }

    public BredAnimalsTrigger.TriggerInstance bredAnimals(Consumer<BredAnimalsBuilder> consumer) {
        BredAnimalsBuilder builder = new BredAnimalsBuilder();
        consumer.accept(builder);
        return new BredAnimalsTrigger.TriggerInstance(builder.player, builder.parent, builder.partner, builder.child);
    }

    public ChangeDimensionTrigger.TriggerInstance changedDimension(Consumer<ChangeDimensionBuilder> consumer) {
        ChangeDimensionBuilder builder = new ChangeDimensionBuilder();
        consumer.accept(builder);
        return new ChangeDimensionTrigger.TriggerInstance(builder.player, builder.from, builder.to);
    }

    public ChanneledLightningTrigger.TriggerInstance channeledLightning(Consumer<ChanneledLightningBuilder> consumer) {
        ChanneledLightningBuilder builder = new ChanneledLightningBuilder();
        consumer.accept(builder);
        return new ChanneledLightningTrigger.TriggerInstance(builder.player, builder.victims);
    }

    public ConstructBeaconTrigger.TriggerInstance constructedBeacon(Consumer<ConstructBeaconBuilder> consumer) {
        ConstructBeaconBuilder builder = new ConstructBeaconBuilder();
        consumer.accept(builder);
        return new ConstructBeaconTrigger.TriggerInstance(builder.player, builder.bounds);
    }

    public CuredZombieVillagerTrigger.TriggerInstance curedZombieVillager(Consumer<CuredZombieVillagerBuilder> consumer) {
        CuredZombieVillagerBuilder builder = new CuredZombieVillagerBuilder();
        consumer.accept(builder);
        return new CuredZombieVillagerTrigger.TriggerInstance(builder.player, builder.zombie, builder.villager);
    }

    public ConsumeItemTrigger.TriggerInstance consumeItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ConsumeItemTrigger.TriggerInstance(builder.player, builder.item);
    }

    //TODO Distance

    public EffectsChangedTrigger.TriggerInstance effectChanged(Consumer<EffectsChangedBuilder> consumer) {
        EffectsChangedBuilder builder = new EffectsChangedBuilder();
        consumer.accept(builder);
        return new EffectsChangedTrigger.TriggerInstance(builder.player, builder.effects, builder.source);
    }

    public EnchantedItemTrigger.TriggerInstance enchantedItem(Consumer<EnchantedItemBuilder> consumer) {
        EnchantedItemBuilder builder = new EnchantedItemBuilder();
        consumer.accept(builder);
        return new EnchantedItemTrigger.TriggerInstance(builder.player, builder.item, builder.levels);
    }

    public EnterBlockTrigger.TriggerInstance entersBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new EnterBlockTrigger.TriggerInstance(builder.player, builder.block, builder.state);
    }

    public EntityHurtPlayerTrigger.TriggerInstance entityHurtPlayer(Consumer<EntityHurtPlayerBuilder> consumer) {
        EntityHurtPlayerBuilder builder = new EntityHurtPlayerBuilder();
        consumer.accept(builder);
        return new EntityHurtPlayerTrigger.TriggerInstance(builder.player, builder.damage);
    }

    public FilledBucketTrigger.TriggerInstance filledBucket(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new FilledBucketTrigger.TriggerInstance(builder.player, builder.item);
    }

    public FishingRodHookedTrigger.TriggerInstance fishingRodHooked(Consumer<FishingRodHookedBuilder> consumer) {
        FishingRodHookedBuilder builder = new FishingRodHookedBuilder();
        consumer.accept(builder);
        return new FishingRodHookedTrigger.TriggerInstance(builder.player, builder.rod, builder.entity, builder.item);
    }

    public InventoryChangeTrigger.TriggerInstance inventoryChange(Consumer<InventoryChangeBuilder> consumer) {
        InventoryChangeBuilder builder = new InventoryChangeBuilder();
        consumer.accept(builder);
        return new InventoryChangeTrigger.TriggerInstance(builder.player, builder.slotsOccupied, builder.slotsFull, builder.slotsEmpty, builder.items);
    }

    public ItemDurabilityTrigger.TriggerInstance itemDurability(Consumer<ItemDurabilityBuilder> consumer) {
        ItemDurabilityBuilder builder = new ItemDurabilityBuilder();
        consumer.accept(builder);
        return new ItemDurabilityTrigger.TriggerInstance(builder.player, builder.item, builder.durability, builder.delta);
    }

    public ItemPickedUpByEntityTrigger.TriggerInstance itemPickedUpByEntity(Consumer<ItemPickedUpByEntityBuilder> consumer) {
        ItemPickedUpByEntityBuilder builder = new ItemPickedUpByEntityBuilder();
        consumer.accept(builder);
        return new ItemPickedUpByEntityTrigger.TriggerInstance(builder.player, builder.item, builder.entity);
    }

    public ItemUsedOnBlockTrigger.TriggerInstance itemUsedOnBlock(Consumer<ItemUsedOnBlockBuilder> consumer) {
        ItemUsedOnBlockBuilder builder = new ItemUsedOnBlockBuilder();
        consumer.accept(builder);
        return new ItemUsedOnBlockTrigger.TriggerInstance(builder.player, builder.location, builder.item);
    }

    public KilledByCrossbowTrigger.TriggerInstance killedByCrossbow(Consumer<KilledByCrossbowBuilder> consumer) {
        KilledByCrossbowBuilder builder = new KilledByCrossbowBuilder();
        consumer.accept(builder);
        return new KilledByCrossbowTrigger.TriggerInstance(builder.player, builder.victims, builder.uniqueEntityTypes);
    }

    public KilledTrigger.TriggerInstance playerKilledEntity(Consumer<PlayerKillEntityBuilder> consumer) {
        PlayerKillEntityBuilder builder = new PlayerKillEntityBuilder();
        consumer.accept(builder);
        return new KilledTrigger.TriggerInstance(CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(), builder.player, builder.killed, builder.killingBlow);
    }

    public static KilledTrigger.TriggerInstance entityKilledPlayer(Consumer<EntityKillPlayerBuilder> consumer) {
        EntityKillPlayerBuilder builder = new EntityKillPlayerBuilder();
        consumer.accept(builder);
        return new KilledTrigger.TriggerInstance(CriteriaTriggers.ENTITY_KILLED_PLAYER.getId(), builder.player, builder.killer, builder.killingBlow);
    }

    public LevitationTrigger.TriggerInstance levitated(Consumer<LevitationBuilder> consumer) {
        LevitationBuilder builder = new LevitationBuilder();
        consumer.accept(builder);
        return new LevitationTrigger.TriggerInstance(builder.player, builder.distance, builder.duration);
    }

    public LightningStrikeTrigger.TriggerInstance lightningStrike(Consumer<LightningStrikeBuilder> consumer) {
        LightningStrikeBuilder builder = new LightningStrikeBuilder();
        consumer.accept(builder);
        return new LightningStrikeTrigger.TriggerInstance(builder.player, builder.lightning, builder.bystander);
    }

    public LootTableTrigger.TriggerInstance lootTable(Consumer<LootTableBuilder> consumer) {
        LootTableBuilder builder = new LootTableBuilder();
        consumer.accept(builder);
        return new LootTableTrigger.TriggerInstance(builder.player, builder.lootTable);
    }

    public PlacedBlockTrigger.TriggerInstance placedBlock(Consumer<PlacedBlockBuilder> consumer) {
        PlacedBlockBuilder builder = new PlacedBlockBuilder();
        consumer.accept(builder);
        return new PlacedBlockTrigger.TriggerInstance(builder.player, builder.block, builder.state, builder.location, builder.item);
    }

    public PlayerHurtEntityTrigger.TriggerInstance playerHurtEntity(Consumer<PlayerHurtEntityBuilder> consumer) {
        PlayerHurtEntityBuilder builder = new PlayerHurtEntityBuilder();
        consumer.accept(builder);
        return new PlayerHurtEntityTrigger.TriggerInstance(builder.player, builder.damage, builder.entity);
    }

    public PlayerInteractTrigger.TriggerInstance playerInteract(Consumer<PlayerInteractBuilder> consumer) {
        PlayerInteractBuilder builder = new PlayerInteractBuilder();
        consumer.accept(builder);
        return new PlayerInteractTrigger.TriggerInstance(builder.player, builder.item, builder.entity);
    }

    public RecipeUnlockedTrigger.TriggerInstance recipeUnlocked(Consumer<RecipeUnlockedBuilder> consumer) {
        RecipeUnlockedBuilder builder = new RecipeUnlockedBuilder();
        consumer.accept(builder);
        return new RecipeUnlockedTrigger.TriggerInstance(builder.player, builder.recipe);
    }

    public ShotCrossbowTrigger.TriggerInstance shotCrossbow(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new ShotCrossbowTrigger.TriggerInstance(builder.player, builder.item);
    }

    public SlideDownBlockTrigger.TriggerInstance slideDownBlock(Consumer<SingleBlockBuilder> consumer) {
        SingleBlockBuilder builder = new SingleBlockBuilder();
        consumer.accept(builder);
        return new SlideDownBlockTrigger.TriggerInstance(builder.player, builder.block, builder.state);
    }

    public StartRidingTrigger.TriggerInstance startRiding(PlayerPredicate playerPredicate) {
        EntityPredicate.Composite player = EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity().player(playerPredicate).build());
        return new StartRidingTrigger.TriggerInstance(player);
    }

    public StartRidingTrigger.TriggerInstance startRiding(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new StartRidingTrigger.TriggerInstance(builder.build());
    }

    public StartRidingTrigger.TriggerInstance startRiding() {
        return new StartRidingTrigger.TriggerInstance(EntityPredicate.Composite.ANY);
    }

    public SummonedEntityTrigger.TriggerInstance summonedEntity(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new SummonedEntityTrigger.TriggerInstance(builder.player, builder.entity);
    }

    public TameAnimalTrigger.TriggerInstance tameAnimal(Consumer<SingleEntityBuilder> consumer) {
        SingleEntityBuilder builder = new SingleEntityBuilder();
        consumer.accept(builder);
        return new TameAnimalTrigger.TriggerInstance(builder.player, builder.entity);
    }

    public TargetBlockTrigger.TriggerInstance targetBlock(Consumer<TargetBlockBuilder> consumer) {
        TargetBlockBuilder builder = new TargetBlockBuilder();
        consumer.accept(builder);
        return new TargetBlockTrigger.TriggerInstance(builder.player, builder.signalStrength, builder.projectile);
    }

    public TickTrigger.TriggerInstance tick(PlayerPredicate playerPredicate) {
        return new TickTrigger.TriggerInstance(EntityPredicate.Composite.wrap(EntityPredicate.Builder.entity().player(playerPredicate).build()));
    }

    public TickTrigger.TriggerInstance tick(Consumer<PlayerPredicateBuilder> consumer) {
        PlayerPredicateBuilder builder = new PlayerPredicateBuilder();
        consumer.accept(builder);
        return new TickTrigger.TriggerInstance(builder.build());
    }

    public TickTrigger.TriggerInstance tick() {
        return tick(PlayerPredicate.ANY);
    }

    public TradeTrigger.TriggerInstance trade(Consumer<TradeBuilder> consumer) {
        TradeBuilder builder = new TradeBuilder();
        consumer.accept(builder);
        return new TradeTrigger.TriggerInstance(builder.player, builder.villager, builder.item);
    }

    public UsedEnderEyeTrigger.TriggerInstance usedEnderEye(Consumer<UsedEnderEyeBuilder> consumer) {
        UsedEnderEyeBuilder builder = new UsedEnderEyeBuilder();
        consumer.accept(builder);
        return new UsedEnderEyeTrigger.TriggerInstance(builder.player, builder.distance);
    }

    public UsedTotemTrigger.TriggerInstance usedTotem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsedTotemTrigger.TriggerInstance(builder.player, builder.item);
    }

    public UsingItemTrigger.TriggerInstance usingItem(Consumer<SingleItemBuilder> consumer) {
        SingleItemBuilder builder = new SingleItemBuilder();
        consumer.accept(builder);
        return new UsingItemTrigger.TriggerInstance(builder.player, builder.item);
    }
}
