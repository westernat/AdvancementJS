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
            .then(CommandManager.literal("example").executes(context -> {
                ServerCommandSource source = context.getSource();
                generate(source, AdvJS.SERVER_EXAMPLE, SERVER_EXAMPLE);
                generate(source, AdvJS.STARTUP_EXAMPLE, STARTUP_EXAMPLE);
                generate(source, AdvJS.CLIENT_EXAMPLE, CLIENT_EXAMPLE);
                return 3;
            }))
            .then(CommandManager.literal("story").executes(context -> generate(context.getSource(), AdvJS.STORY, STORY)))
            .then(CommandManager.literal("adventure").executes(context -> generate(context.getSource(), AdvJS.ADVENTURE, ADVENTURE)))
            .then(CommandManager.literal("nether").executes(context -> generate(context.getSource(), AdvJS.NETHER, NETHER)))
            .then(CommandManager.literal("husbandry").executes(context -> generate(context.getSource(), AdvJS.HUSBANDRY, HUSBANDRY)))
            .then(CommandManager.literal("end").executes(context -> generate(context.getSource(), AdvJS.END, END)))
            .then(CommandManager.literal("all").executes(context -> {
                ServerCommandSource source = context.getSource();
                generate(source, AdvJS.STARTUP_EXAMPLE, STARTUP_EXAMPLE);
                generate(source, AdvJS.SERVER_EXAMPLE, SERVER_EXAMPLE);
                generate(source, AdvJS.STORY, STORY);
                generate(source, AdvJS.ADVENTURE, ADVENTURE);
                generate(source, AdvJS.NETHER, NETHER);
                generate(source, AdvJS.HUSBANDRY, HUSBANDRY);
                generate(source, AdvJS.END, END);
                return 7;
            }))
        );
    }

    private static int generate(ServerCommandSource sourceStack, Path file, String text) {
        try {
            Files.writeString(file, text);
            sourceStack.sendFeedback(() -> Text.translatable("advjs.command.success", file.getFileName()), false);
            return 1;
        } catch (Exception e) {
            throw new CommandException(Text.translatable("advjs.command.failed", file.getFileName()));
        }
    }

    public static final String END;
    public static final String HUSBANDRY;
    public static final String NETHER;
    public static final String ADVENTURE;
    public static final String STORY;
    public static final String SERVER_EXAMPLE;
    public static final String STARTUP_EXAMPLE;
    public static final String CLIENT_EXAMPLE;

    static {
        END = """
            AdvJSEvents.advancement(event => {
                const { TRIGGER } = event;
                        
                const end = event
                    .create("advjs:end")
                    .display(displayBuilder => {
                        displayBuilder.setIcon("end_stone")
                        displayBuilder.setTitle(Text.translate("advancements.end.root.title"))
                        displayBuilder.setDescription(Text.translate("advancements.end.root.description"))
                        displayBuilder.setBackground("textures/gui/advancements/backgrounds/end.png")
                        displayBuilder.setShowToast(false)
                        displayBuilder.setAnnounceToChat(false)
                    })
                    .criteria(criteriaBuilder => {
                        criteriaBuilder.add("entered_end", TRIGGER.changedDimension(triggerBuilder => {
                            triggerBuilder.setTo("the_end")
                        }))
                    });
                        
                const kill_dragon = end
                    .addChild("kill_dragon", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("dragon_head")
                                displayBuilder.setTitle(Text.translate("advancements.end.kill_dragon.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.kill_dragon.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("killed_dragon", TRIGGER.playerKilledEntity(triggerBuilder => {
                                    triggerBuilder.setKilledByType("ender_dragon")
                                }))
                            })
                    });
                        
                const enter_end_gateway = kill_dragon
                    .addChild("enter_end_gateway", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("ender_pearl")
                                displayBuilder.setTitle(Text.translate("advancements.end.enter_end_gateway.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.enter_end_gateway.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("entered_end_gateway", TRIGGER.entersBlock(triggerBuilder => {
                                    triggerBuilder.setBlock("end_gateway")
                                }))
                            })
                    });
                        
                kill_dragon
                    .addChild("respawn_dragon", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("end_crystal")
                                displayBuilder.setTitle(Text.translate("advancements.end.respawn_dragon.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.respawn_dragon.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("summoned_dragon", TRIGGER.summonedEntity(triggerBuilder => {
                                    triggerBuilder.setEntityByType("ender_dragon")
                                }))
                            })
                    });
                        
                const find_end_city = enter_end_gateway
                    .addChild("find_end_city", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("purpur_block")
                                displayBuilder.setTitle(Text.translate("advancements.end.find_end_city.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.find_end_city.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("in_city", TRIGGER.location(triggerBuilder => {
                                    triggerBuilder.setLocation(locationPredicateBuilder => {
                                        locationPredicateBuilder.setStructure("end_city")
                                    })
                                }))
                            })
                    });
                        
                kill_dragon
                    .addChild("dragon_breath", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("dragon_breath")
                                displayBuilder.setTitle(Text.translate("advancements.end.dragon_breath.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.dragon_breath.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("dragon_breath", TRIGGER.hasItems("dragon_breath"))
                            })
                    });
                        
                find_end_city
                    .addChild("levitate", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("shulker_shell")
                                displayBuilder.setTitle(Text.translate("advancements.end.levitate.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.levitate.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("levitated", TRIGGER.levitated(triggerBuilder => {
                                    triggerBuilder.setDistance(distancePredicateBuilder => {
                                        distancePredicateBuilder.setY({ min: 50 })
                                    })
                                }))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(50))
                    });
                        
                find_end_city
                    .addChild("elytra", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("elytra")
                                displayBuilder.setTitle(Text.translate("advancements.end.elytra.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.elytra.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("elytra", TRIGGER.hasItems("elytra"))
                            })
                    });
                        
                kill_dragon
                    .addChild("dragon_egg", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("dragon_egg")
                                displayBuilder.setTitle(Text.translate("advancements.end.dragon_egg.title"))
                                displayBuilder.setDescription(Text.translate("advancements.end.dragon_egg.description"))
                                displayBuilder.setFrameType(FrameType.GOAL)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("dragon_egg", TRIGGER.hasItems("dragon_egg"))
                            })
                    });
            })
            """;
        HUSBANDRY = """
            AdvJSEvents.advancement(event => {
                const { CONDITION, PROVIDER, TRIGGER } = event;
                        
                const husbandry = event
                    .create("advjs:husbandry")
                    .display(displayBuilder => {
                        displayBuilder.setIcon("hay_block")
                        displayBuilder.setTitle(Text.translate("advancements.husbandry.root.title"))
                        displayBuilder.setDescription(Text.translate("advancements.husbandry.root.description"))
                        displayBuilder.setBackground("textures/gui/advancements/backgrounds/husbandry.png")
                        displayBuilder.setShowToast(false)
                        displayBuilder.setAnnounceToChat(false)
                    })
                    .criteria(criteriaBuilder => {
                        criteriaBuilder.add("consumed_item", TRIGGER.consumeItem(triggerBuilder => { }))
                    });
                        
                const plant_seed = husbandry
                    .addChild("plant_seed", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("wheat")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.plant_seed.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.plant_seed.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                const plants = [
                                    "wheat", "pumpkin_stem", "melon_stem", "beetroots",
                                    "nether_wart", "torchflower", "pitcher_pod"
                                ];
                                plants.forEach(plant => {
                                    criteriaBuilder.add(plant, TRIGGER.placedBlock(triggerBuilder => {
                                        triggerBuilder.addCondition(CONDITION.blockStateProperty(plant))
                                    }))
                                })
                            })
                    });
                        
                const breed_an_animal = husbandry
                    .addChild("breed_an_animal", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("wheat")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.breed_an_animal.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.breed_an_animal.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("bred", TRIGGER.bredAnimals(triggerBuilder => { }))
                            })
                    });
                        
                breed_an_animal
                    .addChild("breed_all_animals", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("golden_carrot")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.breed_all_animals.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.breed_all_animals.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                PROVIDER.getBreedableAnimals().forEach(entity => {
                                    criteriaBuilder.add(entity.toString(), TRIGGER.bredAnimals(triggerBuilder => {
                                        triggerBuilder.setChildByType(entity)
                                    }))
                                })
                                PROVIDER.getIndirectlyBreedableAnimals().forEach(entity => {
                                    criteriaBuilder.add(entity.toString(), TRIGGER.bredAnimals(triggerBuilder => {
                                        triggerBuilder.setParentByType(entity)
                                        triggerBuilder.setPartnerByType(entity)
                                    }))
                                })
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                plant_seed
                    .addChild("balanced_diet", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("apple")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.balanced_diet.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.balanced_diet.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                PROVIDER.getEdibleItems().forEach(item => {
                                    criteriaBuilder.add(item.toString(), TRIGGER.usedItem(item))
                                })
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                plant_seed
                    .addChild("obtain_netherite_hoe", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("netherite_hoe")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.netherite_hoe.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.netherite_hoe.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("netherite_hoe", TRIGGER.hasItems("netherite_hoe"))
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(100))
                    });
                        
                const tame_an_animal = husbandry
                    .addChild("tame_an_animal", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("lead")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.tame_an_animal.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.tame_an_animal.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("tamed_animal", TRIGGER.tameAnimal(triggerBuilder => { }))
                            })
                    });
                        
                const fishy_business = husbandry
                    .addChild("fishy_business", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("fishing_rod")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.fishy_business.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.fishy_business.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                PROVIDER.getFish().forEach(item => {
                                    criteriaBuilder.add(item.toString(), TRIGGER.fishingRodHooked(triggerBuilder => {
                                        triggerBuilder.setRod(item)
                                    }))
                                })
                            })
                    });
                        
                const tactical_fishing = fishy_business
                    .addChild("tactical_fishing", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("pufferfish_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.tactical_fishing.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.tactical_fishing.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                PROVIDER.getFish().forEach(item => {
                                    criteriaBuilder.add(item.toString(), TRIGGER.filledBucket(item))
                                })
                            })
                    });
                        
                const axolotl_in_a_bucket = tactical_fishing
                    .addChild("axolotl_in_a_bucket", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("axolotl_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.axolotl_in_a_bucket.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.axolotl_in_a_bucket.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("axolotl_bucket", TRIGGER.filledBucket("axolotl_bucket"))
                            })
                    });
                        
                axolotl_in_a_bucket
                    .addChild("kill_axolotl_target", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("tropical_fish_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.kill_axolotl_target.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.kill_axolotl_target.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("kill_axolotl_target", TRIGGER.effectChanged(triggerBuilder => {
                                    triggerBuilder.setSourceByType("axolotl")
                                }))
                            })
                    });
                        
                tame_an_animal
                    .addChild("complete_catalogue", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("cod")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.complete_catalogue.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.complete_catalogue.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                PROVIDER.getCatVariants().forEach(variant => {
                                    criteriaBuilder.add(variant.toString(), TRIGGER.tameAnimal(triggerBuilder => {
                                        triggerBuilder.setEntity(entityPredicateBuilder => {
                                            entityPredicateBuilder.setTypeSpecific(entityPredicateBuilder.SPECIFIC.cat(variant.path))
                                        })
                                    }))
                                })
                            })
                            .rewards(rewardsBuilder => rewardsBuilder.setExperience(50))
                    });
                        
                const safely_harvest_honey = husbandry
                    .addChild("safely_harvest_honey", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("honey_bottle")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.safely_harvest_honey.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.safely_harvest_honey.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("safely_harvest_honey", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const location = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBlockByType("beehives")
                                            locationPredicateBuilder.setSmokey(true)
                                        });
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("glass_bottle"))
                                    triggerBuilder.addConditions(location, item)
                                }))
                            })
                    });
                        
                const waxables = [
                    "copper_block", "exposed_copper", "weathered_copper", "oxidized_copper",
                    "cut_copper", "exposed_cut_copper", "weathered_cut_copper", "oxidized_cut_copper",
                    "cut_copper_slab", "exposed_cut_copper_slab", "weathered_cut_copper_slab",
                    "oxidized_cut_copper_slab", "cut_copper_stairs", "exposed_cut_copper_stairs",
                    "weathered_cut_copper_stairs", "oxidized_cut_copper_stairs"
                ];
                        
                const wax_on = safely_harvest_honey
                    .addChild("wax_on", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("honey_bottle")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.wax_on.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.wax_on.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("wax_on", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const location = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBlockByType(waxables)
                                        });
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("honeycomb"));
                                    triggerBuilder.addConditions(location, item)
                                }))
                            })
                    });
                        
                wax_on
                    .addChild("wax_off", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("stone_axe")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.wax_off.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.wax_off.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("wax_off", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const location = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBlockByType(waxables.map(block => "waxed_" + block))
                                        });
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("honeycomb"));
                                    triggerBuilder.addConditions(location, item)
                                }))
                            })
                    });
                        
                const tadpole_in_a_bucket = husbandry
                    .addChild("tadpole_in_a_bucket", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("tadpole_bucket")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.tadpole_in_a_bucket.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.tadpole_in_a_bucket.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("tadpole_bucket", TRIGGER.filledBucket("tadpole_bucket"))
                            })
                    });
                        
                const leash_all_frog_variants = tadpole_in_a_bucket
                    .addChild("leash_all_frog_variants", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("lead")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.leash_all_frog_variants.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.leash_all_frog_variants.description"))
                            })
                            .criteria(criteriaBuilder => {
                                const frogVariants = ["temperate", "warm", "cold"];
                                frogVariants.forEach(variant => {
                                    criteriaBuilder.add(variant, TRIGGER.playerInteract(triggerBuilder => {
                                        triggerBuilder.setItem("lead")
                                        triggerBuilder.setEntity(entityPredicateBuilder => {
                                            entityPredicateBuilder.of("frog")
                                            entityPredicateBuilder.setTypeSpecific(entityPredicateBuilder.SPECIFIC.frog(variant))
                                        })
                                    }))
                                })
                            })
                    });
                        
                leash_all_frog_variants
                    .addChild("froglights", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("verdant_froglight")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.froglights.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.froglights.description"))
                                displayBuilder.setFrameType(FrameType.CHALLENGE)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("froglights", TRIGGER.hasItems(["ochre_froglight", "pearlescent_froglight", "verdant_froglight"]))
                            })
                    });
                        
                husbandry
                    .addChild("silk_touch_nest", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("bee_nest")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.silk_touch_nest.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.silk_touch_nest.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("silk_touch_nest", TRIGGER.beeNestDestroyed(triggerBuilder => {
                                    triggerBuilder.setBlock("bee_nest")
                                    triggerBuilder.setItem(itemPredicateBuilder => {
                                        itemPredicateBuilder.hasEnchantment(enchantmentPredicateBuilder => {
                                            enchantmentPredicateBuilder.setEnchantment("silk_touch")
                                            enchantmentPredicateBuilder.setLevel({ min: 1 })
                                        })
                                    })
                                    triggerBuilder.setBounds(3)
                                }))
                            })
                    });
                        
                husbandry
                    .addChild("ride_a_boat_with_a_goat", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("oak_boat")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.ride_a_boat_with_a_goat.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.ride_a_boat_with_a_goat.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("ride_a_boat_with_a_goat", TRIGGER.startRiding(triggerBuilder => {
                                    triggerBuilder.setVehicle(entityPredicateBuilder => {
                                        entityPredicateBuilder.of("boat")
                                        entityPredicateBuilder.setPassengerByType("goat")
                                    })
                                }))
                            })
                    });
                        
                husbandry
                    .addChild("make_a_sign_glow", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("glow_ink_sac")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.make_a_sign_glow.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.make_a_sign_glow.description"))
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("make_a_sign_glow", TRIGGER.itemUsedOnBlock(triggerBuilder => {
                                    const location = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                                blockPredicateBuilder.ofTag("all_signs")
                                            })
                                        });
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("glow_ink_sac"));
                                    triggerBuilder.addConditions(location, item)
                                }))
                            })
                    });
                        
                const allay_deliver_item_to_player = husbandry
                    .addChild("allay_deliver_item_to_player", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("cookie")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.allay_deliver_item_to_player.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.allay_deliver_item_to_player.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("allay_deliver_item_to_player", TRIGGER.itemPickedUpByPlayer(triggerBuilder => {
                                    triggerBuilder.setEntityByType("allay")
                                }))
                            })
                    });
                        
                allay_deliver_item_to_player
                    .addChild("allay_deliver_cake_to_note_block", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("note_block")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.allay_deliver_cake_to_note_block.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.allay_deliver_cake_to_note_block.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("allay_deliver_cake_to_note_block", TRIGGER.allayDropItemOnBlock(triggerBuilder => {
                                    const location = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBlockByType("note_block")
                                        });
                                    const item = CONDITION.matchTool(itemPickedUpByPlayer => itemPickedUpByPlayer.of("cake"));
                                    triggerBuilder.addConditions(location, item)
                                }))
                            })
                    });
                        
                const obtain_sniffer_egg = husbandry
                    .addChild("obtain_sniffer_egg", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("sniffer_egg")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.obtain_sniffer_egg.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.obtain_sniffer_egg.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("obtain_sniffer_egg", TRIGGER.hasItems("sniffer_egg"))
                            })
                    });
                        
                const feed_snifflet = obtain_sniffer_egg
                    .addChild("feed_snifflet", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("torchflower_seeds")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.feed_snifflet.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.feed_snifflet.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.add("feed_snifflet", TRIGGER.playerInteract(triggerBuilder => {
                                    triggerBuilder.setItem("#minecraft:sniffer_food")
                                    triggerBuilder.setEntity(entityPredicateBuilder => {
                                        entityPredicateBuilder.of("sniffer")
                                        entityPredicateBuilder.setFlags(flagsPredicateBuilder => {
                                            flagsPredicateBuilder.isBaby(true)
                                        })
                                    })
                                }))
                            })
                    });
                        
                feed_snifflet
                    .addChild("plant_any_sniffer_seed", advBuilder => {
                        advBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("pitcher_pod")
                                displayBuilder.setTitle(Text.translate("advancements.husbandry.plant_any_sniffer_seed.title"))
                                displayBuilder.setDescription(Text.translate("advancements.husbandry.plant_any_sniffer_seed.description"))
                                displayBuilder.setHidden(true)
                            })
                            .criteria(criteriaBuilder => {
                                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                                criteriaBuilder.add("torchflower", TRIGGER.placedBlock(triggerBuilder => {
                                    triggerBuilder.addCondition(CONDITION.blockStateProperty("torchflower_crop"))
                                }))
                                criteriaBuilder.add("pitcher_pod", TRIGGER.placedBlock(triggerBuilder => {
                                    triggerBuilder.addCondition(CONDITION.blockStateProperty("pitcher_crop"))
                                }))
                            })
                    })
            })
            """;
        NETHER = """
            AdvJSEvents.advancement(event => {
                const { CONDITION, PREDICATE, PROVIDER, TRIGGER } = event;
                        
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
                                        entityPredicateBuilder.setTag("is_projectile")
                                        entityPredicateBuilder.setDirectByType("fireball")
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
                                        entityPredicateBuilder.setLocation(locationPredicateBuilder => {
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
                                        potions.forEach(potion => mobEffectsBuilder.addEffectByPredicate(potion, PREDICATE.anyMobEffectInstance()))
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
                                        potions.forEach(potion => mobEffectsBuilder.addEffectByPredicate(potion, PREDICATE.anyMobEffectInstance()))
                                        effects.forEach(effect => mobEffectsBuilder.addEffectByPredicate(effect, PREDICATE.anyMobEffectInstance()))
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
                                    const block = CONDITION.blockStateProperty("lodestone");
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("compass"));
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
                                    const block = CONDITION
                                        .blockStateProperty("respawn_anchor")
                                        .stateProperties(statePropertiesBuilder => {
                                            statePropertiesBuilder.match("charge", "4")
                                        });
                                    const item = CONDITION.matchTool(itemPredicateBuilder => itemPredicateBuilder.of("glowstone"));
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
                                    CONDITION.entityProperty(entityPredicateBuilder => {
                                        entityPredicateBuilder.setEquipment(equipmentPredicateBuilder => {
                                            equipmentPredicateBuilder.setHead(itemPredicateBuilder => itemPredicateBuilder.of(item))
                                        })
                                    }).invert()
                                ))
                                const PIGLIN = PREDICATE.entity(entityPredicateBuilder => {
                                    entityPredicateBuilder.of("piglin")
                                    entityPredicateBuilder.setFlags(entityflagsPredicateBuilder => {
                                        entityflagsPredicateBuilder.isBaby(false)
                                    })
                                });
                                criteriaBuilder.add("distract_piglin", TRIGGER.itemPickedUpByEntity(triggerBuilder => {
                                    triggerBuilder.setPlayerByCondition(DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE)
                                    triggerBuilder.setItem("#minecraft:piglin_loved")
                                    triggerBuilder.setEntityByPredicate(PIGLIN)
                                }))
                                criteriaBuilder.add("distract_piglin_directly", TRIGGER.playerInteract(triggerBuilder => {
                                    triggerBuilder.setPlayerByCondition(DISTRACT_PIGLIN_PLAYER_ARMOR_PREDICATE)
                                    triggerBuilder.setItem("gold_ingot")
                                    triggerBuilder.setEntityByPredicate(PIGLIN)
                                }))
                            })
                    });
            })
            """;
        ADVENTURE = """
            AdvJSEvents.advancement(event => {
                const { CONDITION, PREDICATE, PROVIDER, TRIGGER } = event;
                        
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
                        
                sleep_in_bed
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
                        
                kill_a_mob
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
                                        damagePredicateBuilder.setType(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.setTag("is_projectile")
                                            damageSourcePredicateBuilder.setDirectByType("arrows")
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
                                        damagePredicateBuilder.setType(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.setTag("is_projectile")
                                            damageSourcePredicateBuilder.setDirectByType("trident")
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
                                        entityPredicateBuilder.setDistance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setHorizontal({ min: 50 })
                                        })
                                    })
                                    triggerBuilder.setKillingBlow(damageSourcePredicateBuilder => {
                                        damageSourcePredicateBuilder.setTag("is_projectile")
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
                                        entityPredicateBuilder.setTag("raiders")
                                        entityPredicateBuilder.setEquipment(equipmentPredicateBuilder => {
                                            const leaderBannerPredicate = PREDICATE.item(predicateBuilder => {
                                                predicateBuilder.of("white_banner")
                                                predicateBuilder.setNbt(leaderNbt)
                                            })
                                            equipmentPredicateBuilder.setHeadByPredicate(leaderBannerPredicate)
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
                                        entityPredicateBuilder.setDistance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setHorizontal({ min: 30 })
                                        })
                                    })
                                }))
                            })
                    });
                        
                sleep_in_bed
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
                                        equipmentPredicateBuilder.setFeet(itemPredicateBuilder => {
                                            itemPredicateBuilder.of("leather_boots")
                                        })
                                    })
                                    triggerBuilder.setSteppingOn(locationPredicateBuilder => {
                                        locationPredicateBuilder.setBlockByType("powder_snow")
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
                                    triggerBuilder.setLightning(entityPredicateBuilder => {
                                        entityPredicateBuilder.setTypeSpecific(entityPredicateBuilder.SPECIFIC.lightningBolt(lightningBoltPredicateBuilder => {
                                            lightningBoltPredicateBuilder.blocksSetOnFire(0)
                                            lightningBoltPredicateBuilder.setEntityStruckByType("villager")
                                        }))
                                        entityPredicateBuilder.setDistance(distancePredicateBuilder => {
                                            distancePredicateBuilder.setAbsolute({ max: 30 })
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
                                    const jukebox = CONDITION
                                        .locationCheck()
                                        .location(locationPredicateBuilder => {
                                            locationPredicateBuilder.setBiome("meadow")
                                            locationPredicateBuilder.setBlockByType("jukebox")
                                        });
                                    const tool = CONDITION.matchTool(itemPredicateBuilder => {
                                        itemPredicateBuilder.setTag("music_discs")
                                    });
                                    triggerBuilder.addCondition(jukebox)
                                    triggerBuilder.addCondition(tool)
                                }))
                            })
                    });
                        
                spyglass_at_ghast
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
                        
                kill_a_mob
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
                                    itemPredicateBuilder.setTag("decorated_pot_sherds")
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
                                const item = PREDICATE.item(itemPredicateBuilder => itemPredicateBuilder.setTag("decorated_pot_sherds"))
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
                                    let conditions = [];
                                    horizontal.forEach(facing => {
                                        let check = CONDITION
                                            .locationCheck()
                                            .location(locationPredicateBuilder => {
                                                let state = PREDICATE.stateProperties(predicateBuilder => {
                                                    predicateBuilder.match("facing", facing.id)
                                                });
                                                locationPredicateBuilder.setBlock(blockPredicateBuilder => {
                                                    blockPredicateBuilder.ofBlocks("comparator")
                                                    blockPredicateBuilder.setPropertiesByPredicate(state)
                                                })
                                            })
                                            .offset([-facing.vec[0], 0, -facing.vec[1]]);
                                        conditions.push(check)
                                    })
                                    triggerBuilder.addCondition(CONDITION.blockStateProperty("chiseled_bookshelf"))
                                    triggerBuilder.addCondition(CONDITION.anyOf(conditions))
                                }))
                                criteriaBuilder.add("chiseled_bookshelf", TRIGGER.placedBlock(triggerBuilder => {
                                    let conditions = [];
                                    horizontal.forEach(facing => {
                                        let state = CONDITION
                                            .blockStateProperty("comparator")
                                            .stateProperties(statePropertiesPredicateBuilder => {
                                                statePropertiesPredicateBuilder.match("facing", facing.id)
                                            });
                                        let location = CONDITION
                                            .locationCheck()
                                            .location(locationPredicateBuilder => {
                                                locationPredicateBuilder.setBlockByType("chiseled_bookshelf")
                                            })
                                            .offset([facing.vec[0], 0, facing.vec[1]]);
                                        conditions.push(CONDITION.allOf(state, location))
                                    })
                                    triggerBuilder.addCondition(CONDITION.anyOf(conditions))
                                }))
                            })
                    });
            })
            """;
        STORY = """
            AdvJSEvents.advancement(event => {
                const { TRIGGER } = event;
                        
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
                        
                mine_diamond
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
                        
                obtain_armor
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
                                        damagePredicateBuilder.setType(damageSourcePredicateBuilder => {
                                            damageSourcePredicateBuilder.setTag("is_projectile")
                                        })
                                        damagePredicateBuilder.isBlocked(true)
                                    })
                                }))
                            })
                    });
                        
                mine_diamond
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
                        
                enter_the_nether
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
        SERVER_EXAMPLE = """
            // You can turn off auto generate in common.properties
            // You can also use '/advjs' command to generate this
            AdvJSEvents.advancement(event => {
                const { PREDICATE, TRIGGER } = event;

                // Define trigger
                const jump5times = TRIGGER.tick(triggerBuilder =>
                    triggerBuilder.addStat(Stats.JUMP, Stats.CUSTOM, { min: 5 }));
                const bred_in_nether = TRIGGER.bredAnimals(triggerBuilder => {
                    triggerBuilder.setChildByPredicate(PREDICATE.entityFromJson({
                        stepping_on: {
                            dimension: "the_nether"
                        }
                    }))
                });
                // AdvJS custom trigger
                const destroy_dirt = TRIGGER.blockDestroyed(triggerBuilder => triggerBuilder.setBlock("dirt"));
                // Your custom trigger
                const get_adv = TRIGGER.custom("advjs:get_adv");

                // Create root advancement
                const root = event.create("advjs:hell")
                    .display(displayBuilder => {
                        displayBuilder.setTitle("AdvancementJS")
                        displayBuilder.setDescription("Quick example")
                        displayBuilder.setIcon("diamond")
                    })
                    .criteria(criteriaBuilder => criteriaBuilder.add("dirt", destroy_dirt))
                    .rewards(rewardsBuilder => {
                        rewardsBuilder.setExperience(100)
                        // AdvJS custom reward
                        rewardsBuilder.addEffect("absorption", 200)
                    })
                    // Make it repeatable
                    .repeatable();

                // Add child for root
                root.addChild("child1", childBuilder => {
                    childBuilder
                        .display(displayBuilder => {
                            displayBuilder.setTitle(Text.red("Holy"))
                            displayBuilder.setDescription(Text.red("Hell starts"))
                        })
                        .criteria(criteriaBuilder => {
                            // 'OR' means that if you want to achieve this advancement,
                            // you just need one of two triggers matched below
                            criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                            criteriaBuilder.add("bred", bred_in_nether)
                            criteriaBuilder.add("jump", jump5times)
                            criteriaBuilder.add("get_adv", get_adv)
                        })
                        .rewards(rewardsBuilder => {
                            rewardsBuilder.setRecipes("minecraft:lodestone", "minecraft:brewing_stand")
                            rewardsBuilder.setExperience(100)
                        })
                });

                // Remove an exist advancement by AdvancementFilter, available filter was writen in doc.
                // you can also remove by id: 'event.remove("minecraft:story/lava_bucket");'
                event.remove({
                    mod: "minecraft",
                    icon: "minecraft:lava_bucket",
                    frame: "task"
                });

                // Modify an exist advancement
                event.get("minecraft:story/smelt_iron")
                    // Apply offset to display
                    .displayOffset(1, 1, true)
                    .modifyDisplay(displayBuilder => displayBuilder.setIcon("diamond_pickaxe"))
                    .addChild("child2", childBuilder => {
                        childBuilder
                            .display(displayBuilder => {
                                displayBuilder.setIcon("recovery_compass")
                                displayBuilder.setTitle('I will come back!')
                                displayBuilder.setDescription(Text.green("Good luck"))
                                // You can also apply display in DisplayBuilder
                                displayBuilder.offset(-1, 0)
                            })
                            // The trigger could also be created from json
                            .criteria(criteriaBuilder => criteriaBuilder.add("go_back_to_home", TRIGGER.fromJson({
                                "trigger": "minecraft:changed_dimension",
                                "conditions": {
                                    "from": "minecraft:the_end",
                                    "to": "minecraft:overworld"
                                }
                            })))
                            // Check if parent done, else it will not be done
                            .requireParentDone()
                    });
            })

            AdvJSEvents.lock(event => {
                event.result("stone_slab", "minecraft:story/smelt_iron");
                event.itemUse("spyglass", "minecraft:story/smelt_iron");
                event.blockInteract("chest", "minecraft:story/smelt_iron");
                event.entityInteract("villager", "minecraft:story/smelt_iron");
            })
            
            // Compat with 'Better Advancements'
            AdvJSEvents.betterAdv(event => {
                event.modify("advjs:hell/child1").posX(0).posY(32).hideLines()
            })
                        
            PlayerEvents.advancement(event => {
                const player = event.getPlayer();
                // The first argument is use for match player predicate, the other two is the matches you defined
                CustomTriggers.of("advjs:get_adv").trigger(player, event.getAdvancement(), player.username)
            })
            """;
        STARTUP_EXAMPLE = """
            // Create a custom trigger
            AdvJSEvents.trigger(event => {
                event.create("advjs:get_adv")
                    // How many matches you defined, how many tests you should put in
                    // In this example, we defined 2 matches
                    .match(advancement => advancement.getId() == "minecraft:story/smelt_iron")
                    .match(playerName => playerName == "Dev")
            })
            """;
        CLIENT_EXAMPLE = """        
            // Compat with Revelationary(EventHorizon)
            AdvJSEvents.revelation(event => {
                event.onFlush((done, removed, isFirst) => {
                    event.player.sendSystemMessage(Text.aqua(done.size() + " advancement has done"))
                    event.player.sendSystemMessage(Text.red(removed.size() + " advancement has removed"))
                    event.player.sendSystemMessage(Text.green((isFirst ? "Is" : "Isn't") + " first flush"))
                })
            })
            """;
    }
}
