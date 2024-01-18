package org.mesdag.advjs;

import dev.latvian.mods.kubejs.CommonProperties;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.script.BindingsEvent;
import dev.latvian.mods.kubejs.script.ScriptType;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.wrap.TypeWrappers;
import net.minecraft.advancements.RequirementsStrategy;
import org.mesdag.advjs.util.Bounds;
import org.mesdag.advjs.util.FrameTypeWrapper;
import org.mesdag.advjs.util.GameTypeWrapper;
import org.mesdag.advjs.util.RequirementsStrategyWrapper;

import java.nio.file.Files;

import static org.mesdag.advjs.configure.Data.*;

public class AdvJSPlugin extends KubeJSPlugin {
    public static boolean DEBUG;

    @Override
    public void registerBindings(BindingsEvent event) {
        event.add("FrameType", FrameTypeWrapper.class);

        event.add("RequirementsStrategy", RequirementsStrategyWrapper.class);
        event.add("AND", RequirementsStrategy.AND);
        event.add("OR", RequirementsStrategy.OR);

        event.add("GameType", GameTypeWrapper.class);
    }

    @Override
    public void registerTypeWrappers(ScriptType type, TypeWrappers typeWrappers) {
        typeWrappers.registerSimple(Bounds.class, Bounds::of);
    }

    @Override
    public void onServerReload() {
        FILTERS.clear();
        GETTER_MAP.clear();
        BUILDER_MAP.clear();
        LOCK_MAP.clear();
        REQUIRE_DONE.clear();
    }

    @Override
    public void loadCommonProperties(CommonProperties properties) {
        DEBUG = properties.get("AdvJSDebug", true);
        example(properties.get("AdvJSExample", true));
    }

    private static void example(boolean generate) {
        if (generate && Files.notExists(AdvJS.EXAMPLE)) {
            try {
                Files.writeString(AdvJS.EXAMPLE, """
                    // You Can Turn Off Auto Generate In common.properties
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
                    """
                );
                ConsoleJS.SERVER.info("AdvJS: Generated advancement.js");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
