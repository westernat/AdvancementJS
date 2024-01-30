# AdvancementJS
Configure Advancements by KubeJS

## Quick Example(for latest version ```2.6.0```)

### Startup script
```js
// Create a custom trigger
AdvJSEvents.trigger(event => {
    event.create("advjs:get_adv")
        // How many matches you defined, how many tests you should put in
        // In this example, we defined 2 matches
        .match(advancement => advancement.getId() == "minecraft:story/smelt_iron")
        .match(playerName => playerName == "Dev")
})
```

### Server script
```js
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
        // AdvJS custom reward
        .rewards(rewardsBuilder => rewardsBuilder.addEffect("absorption", 200));

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
        // Apply offset to display
        .displayOffset(1, 1, true)
        .modifyDisplay(displayBuilder => displayBuilder.setIcon("diamond_pickaxe"))
        .addChild("child2", childBuilder => {
            childBuilder
                .display(displayBuilder => {
                    displayBuilder.setIcon("recovery_compass")
                    displayBuilder.setTitle('I will come back!')
                    displayBuilder.setDescription(Text.green("Good luck"))
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
        });
})

AdvJSEvents.lock(event => {
    // Lock recipe by advancement
    event.result("stone_slab", "minecraft:story/smelt_iron");
})

PlayerEvents.advancement(event => {
    const player = event.getPlayer();
    // The first argument is use for match player predicate,
    // the other two is matches you defined in startup script
    CustomTriggers.of("advjs:get_adv").trigger(player, event.getAdvancement(), player.username)
})
```

### Client script
```js
// Compat with Revelationary(EventHorizon)
AdvJSEvents.revelation(event => {
    event.onFlush((done, removed, isFirst) => {
        event.player.sendSystemMessage(Text.aqua(done.size() + " advancement has done"))
        event.player.sendSystemMessage(Text.red(removed.size() + " advancement has removed"))
        event.player.sendSystemMessage(Text.green(isFirst ? "Is" : "Isn't" + " first flush"))
    })
})
```

# How to reload
Just use ```/reload```

# Features
- You can create a custom trigger in startup event ```AdvJSEvents.trigger```.
- AdvJS custom trigger
  - blockDestroyed: triggers when the player breaks a block.
  - playerTouch: triggers when the player touch an entity.
  - bossEvent: triggers when the play joins a boss fight.
  - increasedKillScore: triggers when the player killed an entity.
- AdvJS custom reward
  - addEffect: to give effect.
- AdvJS custom method
  - displayOffset(offsetX: number, offsetY: number, modifyChildren?: boolean)
    - apply offset to advancement display and its children
  - requireParentDone()
    - check if parent done, else it will not be done.
  - requireOthersDone(requires[]: ResourceLocation...)
    - check if advancements that you put in had done.
- Compat
  - ```Revelationary```(Fabric only), the new client event: ```AdvJSEvents.revelation```
  - ```EventHorizon```(Forge only), the new client event: ```AdvJSEvents.revelation```
