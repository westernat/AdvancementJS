package org.mesdag.advjs.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.mesdag.advjs.AdvJS;

import java.nio.file.Files;
import java.nio.file.Path;

public class AdvCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("advjs").requires(serverCommandSource -> serverCommandSource.hasPermissionLevel(2))
            .then(CommandManager.literal("example").executes(context -> run(context.getSource(), AdvJS.EXAMPLE, EXAMPLE)))
            .then(CommandManager.literal("story").executes(context -> run(context.getSource(), AdvJS.STORY, STORY)))
            .then(CommandManager.literal("adventure").executes(context -> run(context.getSource(), AdvJS.ADVENTURE, ADVENTURE)))
            .then(CommandManager.literal("nether").executes(context -> run(context.getSource(), AdvJS.NETHER, NETHER)))
        );
    }

    private static int run(ServerCommandSource sourceStack, Path file, String text) {
        try {
            Files.writeString(file, text);
            sourceStack.sendFeedback(() -> Text.translatable("advjs.command.success", file.getFileName()), false);
            return 1;
        } catch (Exception e) {
            throw new CommandException(Text.translatable("advjs.command.failed", file.getFileName()));
        }
    }

    public static final String NETHER;
    public static final String ADVENTURE;
    public static final String STORY;
    public static final String EXAMPLE;

    static {
        NETHER = """
            ServerEvents.advancement(event => {
                const { PREDICATE, PROVIDER, TRIGGER } = event;
                        
                const nether = event
                    .create("advjs:nether")
                    .display(displayBuilder => {
                        displayBuilder.setIcon("red_nether_bricks")
                        displayBuilder.setTitle(Text.translate("advancements.nether.root.title"))
                        displayBuilder.setDescription(Text.translate("advancements.nether.root.description"))
                        displayBuilder.setBackground("textures/gui/advancements/backgrounds/nether.png")
                        displayBuilder.setShowToast(false)
                        displayBuilder.setAnnounceToChat(false)
                    })
                    .criteria(criteriaBuilder => {
                        criteriaBuilder.add("entered_nether", TRIGGER.changedDimension(triggerBuilder => {
                            triggerBuilder.setTo("the_nether")
                        }))
                    });
                        
                const return_to_sender = nether
                    .addChild("return_to_sender", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("fire_charge")
                                displayBuilder.setTitle(Text.translate("advancements.nether.return_to_sender.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.return_to_sender.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("killed_ghast", TRIGGER.playerKilledEntity(triggerBuilder => {
                                    triggerBuilder.setKilledByType("ghast")
                                    triggerBuilder.setKillingBlow(entityPredicateBuilder => {
                                        entityPredicateBuilder.tag("is_projectile")
                                        entityPredicateBuilder.directByType("fireball")
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(50))
                    });
                        
                const find_fortress = nether
                    .addChild("find_fortress", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("nether_bricks")
                                displayBuilder.setTitle(Text.translate("advancements.nether.find_fortress.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.find_fortress.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("fortress", TRIGGER.location(triggerBuilder => {
                                    triggerBuilder.setLocation(locationPredicateBuilder => {
                                        locationPredicateBuilder.setStructure("fortress")
                                    })
                                }))
                            })
                    });
                        
                nether
                    .addChild("fast_travel", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("map")
                                displayBuilder.setTitle(Text.translate("advancements.nether.fast_travel.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.fast_travel.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("travelled", TRIGGER.travelledThroughNether(triggerBuilder => {
                                    triggerBuilder.setDistance(distancePredicateBuilder => {
                                        distancePredicateBuilder.setHorizontal({ min: 7000 })
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                return_to_sender
                    .addChild("uneasy_alliance", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("map")
                                displayBuilder.setTitle(Text.translate("advancements.nether.fast_travel.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.fast_travel.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("killed_ghast", TRIGGER.playerKilledEntity(triggerBuilder => {
                                    triggerBuilder.setKilled(entityPredicateBuilder => {
                                        entityPredicateBuilder.of("ghast")
                                        entityPredicateBuilder.located(locationPredicateBuilder => {
                                            locationPredicateBuilder.setDimension("overworld")
                                        })
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                const get_wither_skull = find_fortress
                    .addChild("get_wither_skull", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("wither_skeleton_skull")
                                displayBuilder.setTitle(Text.translate("advancements.nether.get_wither_skull.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.get_wither_skull.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("wither_skull", TRIGGER.hasItems("wither_skeleton_skull"))
                            })
                    });
                        
                const summon_wither = get_wither_skull
                    .addChild("summon_wither", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("nether_star")
                                displayBuilder.setTitle(Text.translate("advancements.nether.summon_wither.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.summon_wither.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("summoned", TRIGGER.summonedEntity(triggerBuilder => {
                                    triggerBuilder.setEntityByType("wither")
                                }))
                            })
                    });
                        
                const obtain_blaze_rod = find_fortress
                    .addChild("obtain_blaze_rod", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("blaze_rod")
                                displayBuilder.setTitle(Text.translate("advancements.nether.obtain_blaze_rod.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.obtain_blaze_rod.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("blaze_rod", TRIGGER.hasItems("blaze_rod"))
                            })
                    });
                        
                const create_beacon = summon_wither
                    .addChild("create_beacon", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("beacon")
                                displayBuilder.setTitle(Text.translate("advancements.nether.create_beacon.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.create_beacon.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("beacon", TRIGGER.constructedBeacon(triggerBuilder => {
                                    triggerBuilder.setLevel({ min: 1 })
                                }))
                            })
                    });
                        
                create_beacon
                    .addChild("create_full_beacon", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("beacon")
                                displayBuilder.setTitle(Text.translate("advancements.nether.create_full_beacon.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.create_full_beacon.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("beacon", TRIGGER.constructedBeacon(triggerBuilder => {
                                    triggerBuilder.setLevel(4)
                                }))
                            })
                    });
                        
                const brew_potion = obtain_blaze_rod
                    .addChild("brew_potion", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("potion")
                                displayBuilder.setTitle(Text.translate("advancements.nether.brew_potion.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.brew_potion.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("potion", TRIGGER.brewedPotion(triggerBuilder => { }))
                            })
                    });
                        
                const potions = [
                    "speed", "slowness", "strength", "jump_boost",
                    "regeneration", "fire_resistance", "water_breathing",
                    "invisibility", "night_vision", "weakness",
                    "poison", "slow_falling", "resistance"
                ];
                        
                const all_potions = brew_potion
                    .addChild("all_potions", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("milk_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.nether.all_potions.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.all_potions.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("all_effects", TRIGGER.effectChanged(triggerBuilder => {
                                    triggerBuilder.setEffects(mobEffectsBuilder => {
                                        potions.forEach(potion => mobEffectsBuilder.add(potion, PREDICATE.mobEffectInstance()))
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                all_potions
                    .addChild("all_effects", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("bucket")
                                displayBuilder.setTitle(Text.translate("advancements.nether.all_effects.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.all_effects.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("all_effects", TRIGGER.effectChanged(triggerBuilder => {
                                    triggerBuilder.setEffects(mobEffectsBuilder => {
                                        const effects = [
                                            "wither", "haste", "mining_fatigue", "levitation",
                                            "glowing", "absorption", "hunger", "nausea",
                                            "conduit_power", "dolphins_grace", "blindness",
                                            "bad_omen", "hero_of_the_village", "darkness"
                                        ];
                                        potions.forEach(potion => mobEffectsBuilder.add(potion, PREDICATE.mobEffectInstance()))
                                        effects.forEach(effect => mobEffectsBuilder.add(effect, PREDICATE.mobEffectInstance()))
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(1000))
                    });
                        
                const obtain_ancient_debris = nether
                    .addChild("obtain_ancient_debris", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("ancient_debris")
                                displayBuilder.setTitle(Text.translate("advancements.nether.obtain_ancient_debris.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.obtain_ancient_debris.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("ancient_debris", TRIGGER.hasItems("ancient_debris"))
                            })
                    });
                        
                obtain_ancient_debris
                    .addChild("netherite_armor", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("netherite_chestplate")
                                displayBuilder.setTitle(Text.translate("advancements.nether.netherite_armor.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.netherite_armor.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("netherite_armor", TRIGGER.hasItems([
                                    "netherite_helmet",
                                    "netherite_chestplate",
                                    "netherite_leggings",
                                    "netherite_boots"
                                ]))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                obtain_ancient_debris
                    .addChild("use_lodestone", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("lodestone")
                                displayBuilder.setTitle(Text.translate("advancements.nether.use_lodestone.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.use_lodestone.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("use_lodestone", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const block = PREDICATE.blockStateProperty("lodestone");
                                    const item = PREDICATE.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("compass"));
                                    triggerBuilder.addConditions(block, item)
                                }))
                            })
                    });
                        
                const obtain_crying_obsidian = nether
                    .addChild("obtain_crying_obsidian", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("crying_obsidian")
                                displayBuilder.setTitle(Text.translate("advancements.nether.obtain_crying_obsidian.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.obtain_crying_obsidian.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("crying_obsidian", TRIGGER.hasItems("crying_obsidian"))
                            })
                    });
                        
                obtain_crying_obsidian
                    .addChild("charge_respawn_anchor", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("respawn_anchor")
                                displayBuilder.setTitle(Text.translate("advancements.nether.charge_respawn_anchor.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.charge_respawn_anchor.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("charge_respawn_anchor", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const block = PREDICATE
                                        .blockStateProperty("respawn_anchor")
                                        .stateProperties(statePropertiesBuilder => {
                                            statePropertiesBuilder.match("charge", "4")
                                        });
                                    const item = PREDICATE.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("glowstone"));
                                    triggerBuilder.addConditions(block, item)
                                }))
                            })
                    });
                        
                const ride_strider = nether
                    .addChild("ride_strider", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("warped_fungus_on_a_stick")
                                displayBuilder.setTitle(Text.translate("advancements.nether.ride_strider.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.ride_strider.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("used_warped_fungus_on_a_stick", TRIGGER.itemDurability(triggerBuilder => {
                                    triggerBuilder.setPlayer(playerPredicateBuilder => {
                                        playerPredicateBuilder.setVehicle(entityPredicateBuilder => {
                                            entityPredicateBuilder.of("strider")
                                        })
                                    })
                                    triggerBuilder.setItem("warped_fungus_on_a_stick")
                                }))
                            })
                    });
                        
                ride_strider
                    .addChild("ride_strider_in_overworld_lava", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("warped_fungus_on_a_stick")
                                displayBuilder.setTitle(Text.translate("advancements.nether.ride_strider_in_overworld_lava.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.ride_strider_in_overworld_lava.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("ride_entity_distance", TRIGGER.rideEntityInLava(triggerBuilder => {
                                    triggerBuilder.setPlayer(playerPredicateBuilder => {
                                        playerPredicateBuilder.setLocation(locationPredicateBuilder => {
                                            locationPredicateBuilder.setDimension("overworld")
                                        })
                                        playerPredicateBuilder.setVehicle(entityPredicateBuilder => {
                                            entityPredicateBuilder.of("strider")
                                        })
                                    })
                                    triggerBuilder.setDistance(distancePredicateBuilder => {
                                        distancePredicateBuilder.setHorizontal({ min: 50 })
                                    })
                                }))
                            })
                    });
                        
                ride_strider
                    .addChild("explore_nether", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("netherite_boots")
                                displayBuilder.setTitle(Text.translate("advancements.nether.explore_nether.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.explore_nether.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                PROVIDER.getNetherBiomes().forEach(biomeKey => {
                                    criteriaBuilder.add(biomeKey.toString(), TRIGGER.location(triggerBuilder => {
                                        triggerBuilder.setLocation(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBiome(biomeKey)
                                        })
                                    }))
                                })
                            })
                    });
                        
                const find_bastion = nether
                    .addChild("find_bastion", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("polished_blackstone_bricks")
                                displayBuilder.setTitle(Text.translate("advancements.nether.find_bastion.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.find_bastion.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("bastion", TRIGGER.location(triggerBuilder => {
                                    triggerBuilder.setLocation(locationPredicateBuilder => {
                                        locationPredicateBuilder.setStructure("bastion_remnant")
                                    })
                                }))
                            })
                    });
                        
                find_bastion
                    .addChild("loot_bastion", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("chest")
                                displayBuilder.setTitle(Text.translate("advancements.nether.loot_bastion.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.loot_bastion.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                criteriaBuilder.add("loot_bastion_other", TRIGGER.lootTableUsed("chests/bastion_other"))
                                criteriaBuilder.add("loot_bastion_treasure", TRIGGER.lootTableUsed("chests/bastion_treasure"))
                                criteriaBuilder.add("loot_bastion_hoglin_stable", TRIGGER.lootTableUsed("chests/bastion_hoglin_stable"))
                                criteriaBuilder.add("loot_bastion_bridge", TRIGGER.lootTableUsed("chests/bastion_bridge"))
                            })
                    });
                        
                nether
                    .addChild("distract_piglin", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("gold_ingot")
                                displayBuilder.setTitle(Text.translate("advancements.nether.distract_piglin.title"))
                                displayBuilder.setDescription(Text.translate("advancements.nether.distract_piglin.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                const DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE = [];
                                const armor = ["golden_helmet", "golden_chestplate", "golden_leggings", "golden_boots"];
                                armor.forEach(item => DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE.push(
                                    PREDICATE.entityProperty(entityPredicateBuilder => {
                                        entityPredicateBuilder.equipment(equipmentPredicateBuilder => {
                                            equipmentPredicateBuilder.head(itemPredicateBuilder => itemPredicateBuilder.of(item))
                                        })
                                    }).invert()
                                ))
                                const PIGLIN = PREDICATE.entity(entityPredicateBuilder => {
                                    entityPredicateBuilder.of("piglin")
                                    entityPredicateBuilder.flags(entityflagsPredicateBuilder => {
                                        entityflagsPredicateBuilder.isBaby(false)
                                    })
                                });
                                criteriaBuilder.add("distract_piglin", TRIGGER.itemPickedUpByEntity(triggerBuilder => {
                                    triggerBuilder.setPlayer(DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE)
                                    triggerBuilder.setItem("#minecraft:piglin_loved")
                                    triggerBuilder.setEntityByPredicate(PIGLIN)
                                }))
                                criteriaBuilder.add("distract_piglin_directly", TRIGGER.playerInteract(triggerBuilder => {
                                    triggerBuilder.setPlayer(DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE)
                                    triggerBuilder.setItem("gold_ingot")
                                    triggerBuilder.setEntityByPredicate(PIGLIN)
                                }))
                            })
                    });
            })
            """;
        ADVENTURE = """
            ServerEvents.advancement(event => {
                const { PREDICATE, PROVIDER, TRIGGER } = event;
                        
                const adventure = event
                    .create("advjs:adventure")
                    .display(displayBuilder => {
                        displayBuilder.setIcon("map")
                        displayBuilder.setTitle(Text.translate("advancements.adventure.root.title"))
                        displayBuilder.setDescription(Text.translate("advancements.adventure.root.description"))
                        displayBuilder.setBackground("textures/gui/advancements/backgrounds/adventure.png")
                        displayBuilder.setShowToast(false)
                        displayBuilder.setAnnounceToChat(false)
                    })
                    .criteria(criteriaBuilder => {
                        criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                        criteriaBuilder.add("killed_something", TRIGGER.playerKilledEntity(triggerBuilder => { }))
                        criteriaBuilder.add("killed_by_something", TRIGGER.entityKilledPlayer(triggerBuilder => { }))
                    });
                        
                const sleep_in_bed = adventure
                    .addChild("sleep_in_bed", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("red_bed")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.sleep_in_bed.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.sleep_in_bed.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("slept_in_bed", TRIGGER.sleptInBed(triggerBuilder => { }))
                            })
                    });
                        
                const adventuring_time = sleep_in_bed
                    .addChild("adventuring_time", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("diamond_boots")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.adventuring_time.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.adventuring_time.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                PROVIDER.getOverworldBiomes().forEach(biomeKey => {
                                    criteriaBuilder.add(biomeKey.toString(), TRIGGER.location(triggerBuilder => {
                                        triggerBuilder.setLocation(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBiome(biomeKey)
                                        })
                                    }))
                                })
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(500))
                    });
                        
                const trade = adventure
                    .addChild("trade", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("emerald")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.trade.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.trade.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("traded", TRIGGER.villagerTrade(triggerBuilder => { }))
                            })
                    });
                        
                trade
                    .addChild("trade_at_world_height", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("emerald")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.trade_at_world_height.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.trade_at_world_height.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("trade_at_world_height", TRIGGER.villagerTrade(triggerBuilder => {
                                    triggerBuilder.setPlayer(playerPredicateBuilder => {
                                        playerPredicateBuilder.setLocation(locationPredicateBuilder => {
                                            locationPredicateBuilder.setY({ min: 319 })
                                        })
                                    })
                                }))
                            })
                    });
                        
                const MOBS_TO_KILL = [
                    "blaze", "cave_spider", "creeper", "drowned", "elder_guardian", "ender_dragon", "enderman", "endermite",
                    "evoker", "ghast", "guardian", "hoglin", "husk", "magma_cube", "phantom", "piglin", "piglin_brute",
                    "pillager", "ravager", "shulker", "silverfish", "skeleton", "slime", "spider", "stray", "vex",
                    "vindicator", "witch", "wither_skeleton", "wither", "zoglin", "zombie_villager", "zombie", "zombified_piglin"
                ];
                        
                const kill_a_mob = adventure
                    .addChild("kill_a_mob", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("iron_sword")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.kill_a_mob.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.kill_a_mob.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                MOBS_TO_KILL.forEach(mob => {
                                    criteriaBuilder.add(mob, TRIGGER.playerKilledEntity(triggerBuilder => {
                                        triggerBuilder.setKilledByType(mob)
                                    }))
                                })
                            })
                    });
                        
                const kill_all_mobs = kill_a_mob
                    .addChild("kill_all_mobs", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("diamond_sword")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.kill_all_mobs.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.kill_all_mobs.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                MOBS_TO_KILL.forEach(mob => {
                                    criteriaBuilder.add(mob, TRIGGER.playerKilledEntity(triggerBuilder => {
                                        triggerBuilder.setKilledByType(mob)
                                    }))
                                })
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                const shoot_arrow = kill_a_mob
                    .addChild("shoot_arrow", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("bow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.shoot_arrow.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.shoot_arrow.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("shot_arrow", TRIGGER.playerHurtEntity(triggerBuilder => {
                                    triggerBuilder.setDamage(damagePredicateBuilder => {
                                        damagePredicateBuilder.type(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.tag("is_projectile")
                                            damageSourcePredicateBuilder.directByType("arrows")
                                        })
                                    })
                                }))
                            })
                    });
                        
                const throw_trident = kill_a_mob
                    .addChild("throw_trident", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("trident")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.throw_trident.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.throw_trident.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("shot_trident", TRIGGER.playerHurtEntity(triggerBuilder => {
                                    triggerBuilder.setDamage(damagePredicateBuilder => {
                                        damagePredicateBuilder.type(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.tag("is_projectile")
                                            damageSourcePredicateBuilder.directByType("trident")
                                        })
                                    })
                                }))
                            })
                    });
                        
                throw_trident
                    .addChild("very_very_frightening", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("trident")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.very_very_frightening.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.very_very_frightening.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("struck_villager", TRIGGER.channeledLightning(triggerBuilder => {
                                    triggerBuilder.addVictimByType("villager")
                                }))
                            })
                    });
                        
                trade
                    .addChild("summon_iron_golem", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("carved_pumpkin")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.summon_iron_golem.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.summon_iron_golem.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("summoned_golem", TRIGGER.summonedEntity(triggerBuilder => {
                                    triggerBuilder.setEntityByType("iron_golem")
                                }))
                            })
                    });
                        
                shoot_arrow
                    .addChild("sniper_duel", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("arrow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.sniper_duel.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.sniper_duel.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("killed_skeleton", TRIGGER.playerKilledEntity(triggerBuilder => {
                                    triggerBuilder.setKilled(entityPredicateBuilder => {
                                        entityPredicateBuilder.of("skeleton")
                                        entityPredicateBuilder.distance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setHorizontal({ min: 50 })
                                        })
                                    })
                                    triggerBuilder.setKillingBlow(damageSourcePredicateBuilder => {
                                        damageSourcePredicateBuilder.tag("is_projectile")
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(50))
                    });
                        
                kill_a_mob
                    .addChild("totem_of_undying", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("totem_of_undying")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.totem_of_undying.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.totem_of_undying.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("used_totem", TRIGGER.usedTotem(triggerBuilder => {
                                    triggerBuilder.setItem("totem_of_undying")
                                }))
                            })
                    });
                        
                const ol_betsy = adventure
                    .addChild("ol_betsy", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("crossbow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.ol_betsy.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.ol_betsy.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("shot_crossbow", TRIGGER.shotCrossbow(triggerBuilder => {
                                    triggerBuilder.setItem("crossbow")
                                }))
                            })
                    });
                        
                ol_betsy
                    .addChild("whos_the_pillager_now", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("crossbow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.whos_the_pillager_now.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.whos_the_pillager_now.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("kill_pillager", TRIGGER.killedByCrossbow(triggerBuilder => {
                                    triggerBuilder.addVictimByType("pillager")
                                }))
                            })
                    });
                        
                ol_betsy
                    .addChild("two_birds_one_arrow", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("crossbow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.two_birds_one_arrow.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.two_birds_one_arrow.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("two_birds", TRIGGER.killedByCrossbow(triggerBuilder => {
                                    triggerBuilder.addVictimByType("phantom")
                                    triggerBuilder.addVictimByType("phantom")
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(65))
                    });
                        
                ol_betsy
                    .addChild("arbalistic", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("crossbow")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.arbalistic.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.arbalistic.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("arbalistic", TRIGGER.killedByCrossbow(triggerBuilder => {
                                    triggerBuilder.setUniqueEntityTypes(5)
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(85))
                    });
                        
                const leaderNbt = {
                    BlockEntityTag: {
                        Patterns: [
                            { Color: 9, Pattern: "mr" }, { Color: 8, Pattern: "bs" },
                            { Color: 7, Pattern: "cs" }, { Color: 8, Pattern: "bo" },
                            { Color: 15, Pattern: "ms" }, { Color: 8, Pattern: "hh" },
                            { Color: 8, Pattern: "mc" }, { Color: 15, Pattern: "bo" }
                        ],
                        id: "minecraft:banner"
                    },
                    HideFlags: 32,
                    display: { Name: '{"color":"gold","translate":"block.minecraft.ominous_banner"}' }
                };
                const leaderBanner = Item.of('minecraft:white_banner', leaderNbt);
                        
                const voluntary_exile = adventure
                    .addChild("voluntary_exile", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon(leaderBanner)
                                displayBuilder.setTitle(Text.translate("advancements.adventure.voluntary_exile.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.voluntary_exile.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("voluntary_exile", TRIGGER.playerKilledEntity(triggerBuilder =>
                                    triggerBuilder.setKilled(entityPredicateBuilder => {
                                        entityPredicateBuilder.tag("raiders")
                                        entityPredicateBuilder.equipment(equipmentPredicateBuilder => {
                                            const leaderBannerPredicate = PREDICATE.item(predicateBuilder => {
                                                predicateBuilder.of("white_banner")
                                                predicateBuilder.hasNbt(leaderNbt)
                                            })
                                            equipmentPredicateBuilder.head(leaderBannerPredicate)
                                        })
                                    })
                                ))
                            })
                    });
                        
                voluntary_exile
                    .addChild("hero_of_the_village", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon(leaderBanner)
                                displayBuilder.setTitle(Text.translate("advancements.adventure.hero_of_the_village.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.hero_of_the_village.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("hero_of_the_village", TRIGGER.raidWon(triggerBuilder => { }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                adventure
                    .addChild("honey_block_slide", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("honey_block")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.honey_block_slide.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.honey_block_slide.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("honey_block_slide", TRIGGER.slideDownBlock(triggerBuilder => {
                                    triggerBuilder.setBlock("honey_block")
                                }))
                            })
                    });
                        
                shoot_arrow
                    .addChild("bullseye", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("target")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.bullseye.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.bullseye.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("bullseye", TRIGGER.targetHit(triggerBuilder => {
                                    triggerBuilder.setSignalStrength(15)
                                    triggerBuilder.setProjectile(entityPredicateBuilder => {
                                        entityPredicateBuilder.distance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setHorizontal({ min: 30 })
                                        })
                                    })
                                }))
                            })
                    });
                        
                const walk_on_powder_snow_with_leather_boots = sleep_in_bed
                    .addChild("walk_on_powder_snow_with_leather_boots", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("leather_boots")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.walk_on_powder_snow_with_leather_boots.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.walk_on_powder_snow_with_leather_boots.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("walk_on_powder_snow_with_leather_boots", TRIGGER.location(triggerBuilder => {
                                    triggerBuilder.setEquipment(equipmentPredicateBuilder => {
                                        equipmentPredicateBuilder.feet(itemPredicateBuilder => {
                                            itemPredicateBuilder.of("leather_boots")
                                        })
                                    })
                                    triggerBuilder.setSteppingOn(locationPredicateBuilder => {
                                        locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                            blockPredicateBuilder.ofBlocks("powder_snow")
                                        })
                                    })
                                }))
                            })
                    });
                        
                adventure
                    .addChild("lightning_rod_with_villager_no_fire", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("lightning_rod")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.lightning_rod_with_villager_no_fire.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.lightning_rod_with_villager_no_fire.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("lightning_rod_with_villager_no_fire", TRIGGER.lightningStrike(triggerBuilder => {
                                    triggerBuilder.setLightning(lightningBoltPredicateBuilder => {
                                        lightningBoltPredicateBuilder.blocksSetOnFire(0)
                                        lightningBoltPredicateBuilder.distance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setAbsolute({ max: 30 })
                                        })
                                        lightningBoltPredicateBuilder.entityStruck(entityPredicateBuilder => {
                                            entityPredicateBuilder.of("villager")
                                        })
                                    })
                                }))
                            })
                    });
                        
                function lookAtThroughSpyglass(entityType) {
                    return TRIGGER.usingItem(triggerBuilder => {
                        triggerBuilder.setPlayer(playerPredicateBuilder => {
                            playerPredicateBuilder.setLookingAt(entityPredicateBuilder => {
                                entityPredicateBuilder.of(entityType)
                            })
                        })
                        triggerBuilder.setItem("spyglass")
                    })
                }
                        
                const spyglass_at_parrot = adventure
                    .addChild("spyglass_at_parrot", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("spyglass")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.spyglass_at_parrot.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.spyglass_at_parrot.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("spyglass_at_parrot", lookAtThroughSpyglass("parrot"))
                            })
                    });
                        
                const spyglass_at_ghast = spyglass_at_parrot
                    .addChild("spyglass_at_ghast", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("spyglass")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.spyglass_at_ghast.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.spyglass_at_ghast.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("spyglass_at_ghast", lookAtThroughSpyglass("ghast"))
                            })
                    });
                        
                sleep_in_bed
                    .addChild("play_jukebox_in_meadows", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("jukebox")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.play_jukebox_in_meadows.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.play_jukebox_in_meadows.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("play_jukebox_in_meadows", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const jukebox = PREDICATE
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBiome("meadow")
                                            locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                                blockPredicateBuilder.ofBlocks("jukebox")
                                            })
                                        });
                                    const tool = PREDICATE.matchTool(itemPredicateBuilder => {
                                        itemPredicateBuilder.tag("music_discs")
                                    });
                                    triggerBuilder.addCondition(jukebox)
                                    triggerBuilder.addCondition(tool)
                                }))
                            })
                    });
                        
                const spyglass_at_dragon = spyglass_at_ghast
                    .addChild("spyglass_at_dragon", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("spyglass")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.spyglass_at_dragon.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.spyglass_at_dragon.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("spyglass_at_ghast", lookAtThroughSpyglass("ender_dragon"))
                            })
                    });
                        
                adventure
                    .addChild("fall_from_world_height", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("water_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.fall_from_world_height.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.fall_from_world_height.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("fall_from_world_height", TRIGGER.fallFromHeight(triggerBuilder => {
                                    triggerBuilder.setPlayer(playerPredicateBuilder => {
                                        playerPredicateBuilder.setLocation(locationPredicateBuilder => {
                                            locationPredicateBuilder.setY({ max: -59 })
                                        })
                                    })
                                    triggerBuilder.setDistance(distancePredicateBuilder => {
                                        distancePredicateBuilder.setY({ min: 379 })
                                    })
                                    triggerBuilder.setStartPosition(locationPredicateBuilder => {
                                        locationPredicateBuilder.setY({ min: 319 })
                                    })
                                }))
                            })
                    });
                        
                const kill_mob_near_sculk_catalyst = kill_a_mob
                    .addChild("kill_mob_near_sculk_catalyst", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("sculk_catalyst")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.kill_mob_near_sculk_catalyst.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.kill_mob_near_sculk_catalyst.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("kill_mob_near_sculk_catalyst", TRIGGER.playerKilledEntityNearSculkCatalyst(triggerBuilder => { }))
                            })
                    });
                        
                adventure
                    .addChild("avoid_vibration", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("sculk_sensor")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.avoid_vibration.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.avoid_vibration.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("avoid_vibration", TRIGGER.avoidVibration(triggerBuilder => { }))
                            })
                    });
                        
                const salvage_sherd = adventure
                    .addChild("salvage_sherd", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("brush")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.salvage_sherd.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.salvage_sherd.description"))
                            })
                            .criteria(criteriaBuilder => {
                                const sherds = [
                                    "desert_pyramid", "desert_well", "ocean_ruin_cold",
                                    "ocean_ruin_warm", "trail_ruins_rare", "trail_ruins_common"
                                ];
                                sherds.forEach(sherd => criteriaBuilder.add(sherd, TRIGGER.lootTableUsed("archaeology/" + sherd)))
                                let item = PREDICATE.item(itemPredicateBuilder => {
                                    itemPredicateBuilder.tag("decorated_pot_sherds")
                                });
                                criteriaBuilder.add("has_sherd", TRIGGER.hasItems(item))
                                criteriaBuilder.setRequirements([sherds, ["has_sherd"]])
                            })
                    });
                        
                salvage_sherd
                    .addChild("craft_decorated_pot_using_only_sherds", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon(Item.of("minecraft:decorated_pot", {
                                    BlockEntityTag: {
                                        id: "minecraft:decorated_pot",
                                        sherds: [
                                            "minecraft:brick",
                                            "minecraft:heart_pottery_sherd",
                                            "minecraft:brick",
                                            "minecraft:explorer_pottery_sherd"
                                        ]
                                    }
                                }))
                                displayBuilder.setTitle(Text.translate("advancements.adventure.craft_decorated_pot_using_only_sherds.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.craft_decorated_pot_using_only_sherds.description"))
                            })
                            .criteria(criteriaBuilder => {
                                const item = PREDICATE.item(itemPredicateBuilder => itemPredicateBuilder.tag("decorated_pot_sherds"))
                                criteriaBuilder.add("pot_crafted_using_only_sherds", TRIGGER.craftedItem("decorated_pot", [item, item, item, item]))
                            })
                    })
                        
                const trim_with_any_armor_pattern = adventure
                    .addChild("trim_with_any_armor_pattern", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("dune_armor_trim_smithing_template")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.craft_decorated_pot_using_only_sherds.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.craft_decorated_pot_using_only_sherds.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                PROVIDER.getSmithingTrims().forEach(trim => {
                                    criteriaBuilder.add("armor_trimmed_" + trim, TRIGGER.craftedItem(trim))
                                })
                            })
                    });
                        
                trim_with_any_armor_pattern
                    .addChild("trim_with_all_exclusive_armor_patterns", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("silence_armor_trim_smithing_template")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.trim_with_all_exclusive_armor_patterns.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.trim_with_all_exclusive_armor_patterns.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.AND)
                                const patterns = [
                                    "spire", "snout", "rib", "ward",
                                    "silence", "vex", "tide", "wayfinder"
                                ];
                                patterns.forEach(pattern => {
                                    let id = "minecraft:" + pattern + "_armor_trim_smithing_template_smithing_trim";
                                    criteriaBuilder.add("armor_trimmed_" + id, TRIGGER.craftedItem(id))
                                })
                            })
                    });
                        
                adventure
                    .addChild("read_power_from_chiseled_bookshelf", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("chiseled_bookshelf")
                                displayBuilder.setTitle(Text.translate("advancements.adventure.read_power_from_chiseled_bookshelf.title"))
                                displayBuilder.setDescription(Text.translate("advancements.adventure.read_power_from_chiseled_bookshelf.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                const horizontal = [
                                    { vec: [1, 0], id: "east" },
                                    { vec: [0, -1], id: "south" },
                                    { vec: [-1, 0], id: "west" },
                                    { vec: [0, 1], id: "north" }
                                ];
                                criteriaBuilder.add("chiseled_bookshelf", TRIGGER.placedBlock(triggerBuilder => {
                                    let checks = [];
                                    horizontal.forEach(facing => {
                                        let check = PREDICATE
                                            .locationCheck()
                                            .location(locationPredicateBuilder => {
                                                let state = PREDICATE.stateProperties(predicateBuilder => {
                                                    predicateBuilder.match("facing", facing.id)
                                                });
                                                locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                                    blockPredicateBuilder.ofBlocks("comparator")
                                                    blockPredicateBuilder.setProperties(state)
                                                })
                                            })
                                            .offset([-facing.vec[0], 0, -facing.vec[1]]);
                                        checks.push(check)
                                    })
                                    triggerBuilder.addCondition(PREDICATE.blockStateProperty("chiseled_bookshelf"))
                                    triggerBuilder.addCondition(PREDICATE.anyOf(checks))
                                }))
                                criteriaBuilder.add("chiseled_bookshelf", TRIGGER.placedBlock(triggerBuilder => {
                                    let checks = [];
                                    horizontal.forEach(facing => {
                                        let state = PREDICATE
                                            .blockStateProperty("comparator")
                                            .stateProperties(statePropertiesPredicateBuilder => {
                                                statePropertiesPredicateBuilder.match("facing", facing.id)
                                            });
                                        let location = PREDICATE
                                            .locationCheck()
                                            .location(locationPredicateBuilder => {
                                                locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                                    blockPredicateBuilder.ofBlocks("chiseled_bookshelf")
                                                })
                                            })
                                            .offset([facing.vec[0], 0, facing.vec[1]]);
                                        checks.push(PREDICATE.allOf(state, location))
                                    })
                                    triggerBuilder.addCondition(PREDICATE.anyOf(checks))
                                }))
                            })
                    });
            })
            """;
        STORY = """
            ServerEvents.advancement(event => {
                const { PREDICATE, TRIGGER } = event;
                        
                const story = event
                    .create("advjs:story")
                    .display(displayBuilder => {
                        displayBuilder.setIcon("grass_block")
                        displayBuilder.setTitle(Text.translate("advancements.story.root.title"))
                        displayBuilder.setDescription(Text.translate("advancements.story.root.description"))
                        displayBuilder.setBackground("textures/gui/advancements/backgrounds/stone.png")
                        displayBuilder.setShowToast(false)
                        displayBuilder.setAnnounceToChat(false)
                    })
                    .criteria(criteriaBuilder => {
                        criteriaBuilder.add("crafting_table", TRIGGER.hasItems("crafting_table"))
                    });
                        
                const mine_stone = story
                    .addChild("mine_stone", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("wooden_pickaxe")
                                displayBuilder.setTitle(Text.translate("advancements.story.mine_stone.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.mine_stone.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("get_stone", TRIGGER.hasItems("#minecraft:stone_tool_materials"))
                            })
                    });
                        
                const upgrade_tools = mine_stone
                    .addChild("upgrade_tools", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("stone_pickaxe")
                                displayBuilder.setTitle(Text.translate("advancements.story.upgrade_tools.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.upgrade_tools.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("stone_pickaxe", TRIGGER.hasItems("stone_pickaxe"))
                            })
                    });
                        
                const smelt_iron = upgrade_tools
                    .addChild("smelt_iron", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("iron_ingot")
                                displayBuilder.setTitle(Text.translate("advancements.story.smelt_iron.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.smelt_iron.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("iron", TRIGGER.hasItems("iron_ingot"))
                            })
                    });
                        
                const iron_tools = smelt_iron
                    .addChild("iron_tools", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("iron_pickaxe")
                                displayBuilder.setTitle(Text.translate("advancements.story.iron_tools.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.iron_tools.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("iron_pickaxe", TRIGGER.hasItems("iron_pickaxe"))
                            })
                    });
                        
                const mine_diamond = iron_tools
                    .addChild("mine_diamond", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("diamond")
                                displayBuilder.setTitle(Text.translate("advancements.story.mine_diamond.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.mine_diamond.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("diamond", TRIGGER.hasItems("diamond"))
                            })
                    });
                        
                const lava_bucket = smelt_iron
                    .addChild("lava_bucket", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("lava_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.story.lava_bucket.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.lava_bucket.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("lava_bucket", TRIGGER.hasItems("lava_bucket"))
                            })
                    });
                        
                const obtain_armor = smelt_iron
                    .addChild("obtain_armor", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("iron_chestplate")
                                displayBuilder.setTitle(Text.translate("advancements.story.obtain_armor.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.obtain_armor.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                criteriaBuilder.add("iron_helmet", TRIGGER.hasItems("iron_helmet"))
                                criteriaBuilder.add("iron_chestplate", TRIGGER.hasItems("iron_chestplate"))
                                criteriaBuilder.add("iron_leggings", TRIGGER.hasItems("iron_leggings"))
                                criteriaBuilder.add("iron_boots", TRIGGER.hasItems("iron_boots"))
                            })
                    });
                        
                const enchant_item = mine_diamond
                    .addChild("enchant_item", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("enchanted_book")
                                displayBuilder.setTitle(Text.translate("advancements.story.enchant_item.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.enchant_item.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("enchanted_item", TRIGGER.enchantedItem(triggerBuilder => { }))
                            })
                    });
                        
                const form_obsidian = lava_bucket
                    .addChild("form_obsidian", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("obsidian")
                                displayBuilder.setTitle(Text.translate("advancements.story.form_obsidian.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.form_obsidian.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("obsidian", TRIGGER.hasItems("obsidian"))
                            })
                    })
                        
                const deflect_arrow = obtain_armor
                    .addChild("deflect_arrow", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("shield")
                                displayBuilder.setTitle(Text.translate("advancements.story.deflect_arrow.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.deflect_arrow.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("deflected_projectile", TRIGGER.entityHurtPlayer(triggerBuilder => {
                                    triggerBuilder.setDamage(damagePredicateBuilder => {
                                        damagePredicateBuilder.type(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.tag("is_projectile")
                                        })
                                        damagePredicateBuilder.blocked(true)
                                    })
                                }))
                            })
                    });
                        
                const shiny_gear = mine_diamond
                    .addChild("shiny_gear", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("diamond_chestplate")
                                displayBuilder.setTitle(Text.translate("advancements.story.shiny_gear.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.shiny_gear.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                criteriaBuilder.add("diamond_helmet", TRIGGER.hasItems("diamond_helmet"))
                                criteriaBuilder.add("diamond_chestplate", TRIGGER.hasItems("diamond_chestplate"))
                                criteriaBuilder.add("diamond_leggings", TRIGGER.hasItems("diamond_leggings"))
                                criteriaBuilder.add("diamond_boots", TRIGGER.hasItems("diamond_boots"))
                            })
                    });
                        
                const enter_the_nether = form_obsidian
                    .addChild("enter_the_nether", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("flint_and_steel")
                                displayBuilder.setTitle(Text.translate("advancements.story.enter_the_nether.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.enter_the_nether.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("entered_nether", TRIGGER.changedDimension(triggerBuilder => {
                                    triggerBuilder.setTo("the_nether")
                                }))
                            })
                    });
                        
                const cure_zombie_villager = enter_the_nether
                    .addChild("cure_zombie_villager", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("golden_apple")
                                displayBuilder.setTitle(Text.translate("advancements.story.cure_zombie_villager.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.cure_zombie_villager.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("cured_zombie", TRIGGER.curedZombieVillager(triggerBuilder => { }))
                            })
                    });
                        
                const follow_ender_eye = enter_the_nether
                    .addChild("follow_ender_eye", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("ender_eye")
                                displayBuilder.setTitle(Text.translate("advancements.story.follow_ender_eye.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.follow_ender_eye.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("in_stronghold", TRIGGER.location(triggerBuilder => {
                                    triggerBuilder.setLocation(locationPredicateBuilder => {
                                        locationPredicateBuilder.setStructure("stronghold")
                                    })
                                }))
                            })
                    });
                        
                follow_ender_eye
                    .addChild("enter_the_end", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("end_stone")
                                displayBuilder.setTitle(Text.translate("advancements.story.enter_the_end.title"))
                                displayBuilder.setDescription(Text.translate("advancements.story.enter_the_end.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("entered_end", TRIGGER.changedDimension(triggerBuilder => {
                                    triggerBuilder.setTo("the_end")
                                }))
                            })
                    });
            })
            """;
        EXAMPLE = """
            // You Can Turn Off Auto Generate In common.properties
            // You can also use '/advjs' command to generate this;
            ServerEvents.advancement((event) => {
                const { PREDICATE, TRIGGER } = event;

                // Define trigger
                const jump5times = TRIGGER.tick((triggerBuilder) =>
                    triggerBuilder.addStat(Stats.JUMP, Stats.CUSTOM, {min: 5}));
                const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
                    triggerBuilder.setChildByPredicate(PREDICATE.entity({
                        stepping_on: {
                            dimension: "the_nether"
                        }
                    }))
                });
                // AdvJS custom trigger
                const destroy_dirt = TRIGGER.blockDestroyed((triggerBuilder) => triggerBuilder.setBlock("dirt"));

                // Create root advancement
                const root = event.create("advjs:hell")
                    .display((displayBuilder) => {
                        displayBuilder.setTitle("AdvancementJS")
                        displayBuilder.setDescription("Quick example")
                        displayBuilder.setIcon("diamond")
                    })
                    .criteria((criteriaBuilder) => criteriaBuilder.add("dirt", destroy_dirt))
                    // AdvJS custom reward
                    .rewards((rewardsBuilder) => rewardsBuilder.addEffect("absorption", 200));

                // Add child for root
                root.addChild("child1", (childBuilder) => {
                    childBuilder
                        .display((displayBuilder) => {
                            displayBuilder.setTitle(Text.red("Holy"))
                            displayBuilder.setDescription(Text.red("Hell starts"))
                        })
                        .criteria((criteriaBuilder) => {
                            // 'OR' means that if you want to achieve this advancement,
                            // you just need one of two triggers matched below
                            criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                            criteriaBuilder.add("bred", bred_in_nether)
                            criteriaBuilder.add("jump", jump5times)
                        })
                        .rewards((rewardsBuilder) => {
                            rewardsBuilder.setRecipes("minecraft:lodestone", "minecraft:brewing_stand")
                            rewardsBuilder.setExperience(100)
                        })
                        // Check if parent done, else it will not be done
                        .requireParentDone()
                });

                // Remove an exist advancement by RemoveFilter, available filter was writen in doc.
                // you can also remove like this: 'event.remove("minecraft:story/lava_bucket");'
                event.remove({
                    icon: "minecraft:lava_bucket"
                });

                // Modify an exist advancement
                event.get("minecraft:story/smelt_iron")
                    .modifyDisplay((displayBuilder) => displayBuilder.setIcon("diamond_pickaxe"))
                    .addChild("child2", (childBuilder) => {
                        childBuilder
                            .display((displayBuilder) => {
                                displayBuilder.setTitle('A nice one!')
                                displayBuilder.setDescription(Text.green("Good luck"))
                            })
                            .criteria((criteriaBuilder) => criteriaBuilder.add("jump", jump5times))
                    });

                // Lock recipe by advancement
                event.lock("stone_slab", "minecraft:story/smelt_iron");
            })
            """;
    }
}
