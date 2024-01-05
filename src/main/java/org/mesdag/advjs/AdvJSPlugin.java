package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.world.GameMode;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;
import org.mesdag.advjs.util.StatsWrapper;

import java.nio.file.Files;

public class AdvJSPlugin extends KubeJSPlugin {
    public static boolean DEBUG;

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);
        event.add("TASK", AdvancementFrame.TASK);
        event.add("GOAL", AdvancementFrame.GOAL);
        event.add("CHALLENGE", AdvancementFrame.CHALLENGE);

        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("AND", CriterionMerger.AND);
        event.add("OR", CriterionMerger.OR);

        event.add("Stats", StatsWrapper.class);

        event.add("GameType", GameTypeWrapper.class);
        event.add("SURVIVAL", GameMode.SURVIVAL);
        event.add("CREATIVE", GameMode.CREATIVE);
        event.add("ADVENTURE", GameMode.ADVENTURE);
        event.add("SPECTATOR", GameMode.SPECTATOR);
    }

    @Override
    public void loadCommonProperties(CommonProperties properties) {
        boolean generate = properties.get("AdvJSExample", true);
        DEBUG = properties.get("AdvJSDebug", true);
        example(generate);
    }

    private static void example(boolean generate) {
        if (generate && Files.notExists(AdvJS.EXAMPLE)) {
            try {
                Files.writeString(AdvJS.EXAMPLE, """
                    // You Can Turn Off Auto Generate This Example In common.properties
                    ServerEvents.advancement((event) => {
                        const { BOUNDS, PREDICATE, TRIGGER } = event;

                        // Define triggers
                        const jump5times = TRIGGER.tick((triggerBuilder) =>
                            triggerBuilder.addStat(Stats.JUMP, Stats.CUSTOM, BOUNDS.min$Integer(5)));
                        const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
                            triggerBuilder.setChild(PREDICATE.entity({
                                stepping_on: {
                                    dimension: "the_nether"
                                }
                            }))
                        });

                        // Create root advancement
                        const root = event.create("advjs:hell")
                            .display((displayBuilder) => {
                                displayBuilder.setTitle("AdvancementJS")
                                displayBuilder.setDescription("Quick example")
                                displayBuilder.setIcon("diamond")
                            })
                            .criteria((criteriaBuilder) => criteriaBuilder.add("tick", TRIGGER.tick()));

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
                        });

                        // Remove an exist advancement
                        event.remove("minecraft:story/lava_bucket")

                        // modify an exist advancement
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
                    })
                    """
                );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            AdvJS.LOGGER.info("Generated advancement.js");
        }
    }
}
