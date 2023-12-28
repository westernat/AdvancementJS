package org.mesdag.advjs.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface StatsWrapper {
    StatType<Block> BLOCK_MINED = Stats.BLOCK_MINED;
    StatType<Item> ITEM_CRAFTED = Stats.ITEM_CRAFTED;
    StatType<Item> ITEM_USED = Stats.ITEM_USED;
    StatType<Item> ITEM_BROKEN = Stats.ITEM_BROKEN;
    StatType<Item> ITEM_PICKED_UP = Stats.ITEM_PICKED_UP;
    StatType<Item> ITEM_DROPPED = Stats.ITEM_DROPPED;
    StatType<EntityType<?>> KILLED = Stats.ENTITY_KILLED;
    StatType<EntityType<?>> KILLED_BY = Stats.ENTITY_KILLED_BY;
    StatType<ResourceLocation> CUSTOM = Stats.CUSTOM;

    ResourceLocation LEAVE_GAME = Stats.LEAVE_GAME;
    ResourceLocation PLAY_TIME = Stats.PLAY_TIME;
    ResourceLocation TOTAL_WORLD_TIME = Stats.TOTAL_WORLD_TIME;
    ResourceLocation TIME_SINCE_DEATH = Stats.TIME_SINCE_DEATH;
    ResourceLocation TIME_SINCE_REST = Stats.TIME_SINCE_REST;
    ResourceLocation CROUCH_TIME = Stats.PLAY_TIME;
    ResourceLocation WALK_ONE_CM = Stats.WALK_ONE_CM;
    ResourceLocation CROUCH_ONE_CM = Stats.CROUCH_ONE_CM;
    ResourceLocation SPRINT_ONE_CM = Stats.SPRINT_ONE_CM;
    ResourceLocation WALK_ON_WATER_ONE_CM = Stats.WALK_ON_WATER_ONE_CM;
    ResourceLocation FALL_ONE_CM = Stats.FALL_ONE_CM;
    ResourceLocation CLIMB_ONE_CM = Stats.CLIMB_ONE_CM;
    ResourceLocation FLY_ONE_CM = Stats.FLY_ONE_CM;
    ResourceLocation WALK_UNDER_WATER_ONE_CM = Stats.WALK_UNDER_WATER_ONE_CM;
    ResourceLocation MINECART_ONE_CM = Stats.MINECART_ONE_CM;
    ResourceLocation BOAT_ONE_CM = Stats.BOAT_ONE_CM;
    ResourceLocation PIG_ONE_CM = Stats.PIG_ONE_CM;
    ResourceLocation HORSE_ONE_CM = Stats.HORSE_ONE_CM;
    ResourceLocation AVIATE_ONE_CM = Stats.AVIATE_ONE_CM;
    ResourceLocation SWIM_ONE_CM = Stats.SWIM_ONE_CM;
    ResourceLocation STRIDER_ONE_CM = Stats.STRIDER_ONE_CM;
    ResourceLocation JUMP = Stats.JUMP;
    ResourceLocation DROP = Stats.DROP;
    ResourceLocation DAMAGE_DEALT = Stats.DAMAGE_DEALT;
    ResourceLocation DAMAGE_DEALT_ABSORBED = Stats.DAMAGE_DEALT_ABSORBED;
    ResourceLocation DAMAGE_DEALT_RESISTED = Stats.DAMAGE_DEALT_RESISTED;
    ResourceLocation DAMAGE_TAKEN = Stats.DAMAGE_TAKEN;
    ResourceLocation DAMAGE_BLOCKED_BY_SHIELD = Stats.DAMAGE_BLOCKED_BY_SHIELD;
    ResourceLocation DAMAGE_ABSORBED = Stats.DAMAGE_ABSORBED;
    ResourceLocation DAMAGE_RESISTED = Stats.DAMAGE_RESISTED;
    ResourceLocation DEATHS = Stats.DEATHS;
    ResourceLocation MOB_KILLS = Stats.MOB_KILLS;
    ResourceLocation ANIMALS_BRED = Stats.ANIMALS_BRED;
    ResourceLocation PLAYER_KILLS = Stats.PLAYER_KILLS;
    ResourceLocation FISH_CAUGHT = Stats.FISH_CAUGHT;
    ResourceLocation TALKED_TO_VILLAGER = Stats.TALKED_TO_VILLAGER;
    ResourceLocation TRADED_WITH_VILLAGER = Stats.TRADED_WITH_VILLAGER;
    ResourceLocation EAT_CAKE_SLICE = Stats.EAT_CAKE_SLICE;
    ResourceLocation FILL_CAULDRON = Stats.FILL_CAULDRON;
    ResourceLocation USE_CAULDRON = Stats.USE_CAULDRON;
    ResourceLocation CLEAN_ARMOR = Stats.CLEAN_ARMOR;
    ResourceLocation CLEAN_BANNER = Stats.CLEAN_BANNER;
    ResourceLocation CLEAN_SHULKER_BOX = Stats.CLEAN_SHULKER_BOX;
    ResourceLocation INTERACT_WITH_BREWINGSTAND = Stats.INTERACT_WITH_BREWINGSTAND;
    ResourceLocation INTERACT_WITH_BEACON = Stats.INTERACT_WITH_BEACON;
    ResourceLocation INSPECT_DROPPER = Stats.INSPECT_DROPPER;
    ResourceLocation INSPECT_HOPPER = Stats.INSPECT_HOPPER;
    ResourceLocation INSPECT_DISPENSER = Stats.INSPECT_DISPENSER;
    ResourceLocation PLAY_NOTEBLOCK = Stats.PLAY_NOTEBLOCK;
    ResourceLocation TUNE_NOTEBLOCK = Stats.TUNE_NOTEBLOCK;
    ResourceLocation POT_FLOWER = Stats.POT_FLOWER;
    ResourceLocation TRIGGER_TRAPPED_CHEST = Stats.TRIGGER_TRAPPED_CHEST;
    ResourceLocation OPEN_ENDERCHEST = Stats.OPEN_ENDERCHEST;
    ResourceLocation ENCHANT_ITEM = Stats.ENCHANT_ITEM;
    ResourceLocation PLAY_RECORD = Stats.PLAY_RECORD;
    ResourceLocation INTERACT_WITH_FURNACE = Stats.INTERACT_WITH_FURNACE;
    ResourceLocation INTERACT_WITH_CRAFTING_TABLE = Stats.INTERACT_WITH_CRAFTING_TABLE;
    ResourceLocation OPEN_CHEST = Stats.OPEN_CHEST;
    ResourceLocation SLEEP_IN_BED = Stats.SLEEP_IN_BED;
    ResourceLocation OPEN_SHULKER_BOX = Stats.OPEN_SHULKER_BOX;
    ResourceLocation OPEN_BARREL = Stats.OPEN_BARREL;
    ResourceLocation INTERACT_WITH_BLAST_FURNACE = Stats.INTERACT_WITH_BLAST_FURNACE;
    ResourceLocation INTERACT_WITH_SMOKER = Stats.INTERACT_WITH_SMOKER;
    ResourceLocation INTERACT_WITH_LECTERN = Stats.INTERACT_WITH_LECTERN;
    ResourceLocation INTERACT_WITH_CAMPFIRE = Stats.INTERACT_WITH_CAMPFIRE;
    ResourceLocation INTERACT_WITH_CARTOGRAPHY_TABLE = Stats.INTERACT_WITH_CARTOGRAPHY_TABLE;
    ResourceLocation INTERACT_WITH_LOOM = Stats.INTERACT_WITH_LOOM;
    ResourceLocation INTERACT_WITH_STONECUTTER = Stats.INTERACT_WITH_STONECUTTER;
    ResourceLocation BELL_RING = Stats.BELL_RING;
    ResourceLocation RAID_TRIGGER = Stats.RAID_TRIGGER;
    ResourceLocation RAID_WIN = Stats.RAID_WIN;
    ResourceLocation INTERACT_WITH_ANVIL = Stats.INTERACT_WITH_ANVIL;
    ResourceLocation INTERACT_WITH_GRINDSTONE = Stats.INTERACT_WITH_GRINDSTONE;
    ResourceLocation TARGET_HIT = Stats.TARGET_HIT;
    ResourceLocation INTERACT_WITH_SMITHING_TABLE = Stats.INTERACT_WITH_SMITHING_TABLE;
}
