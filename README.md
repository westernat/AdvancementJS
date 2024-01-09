# AdvancementJS

Configure Advancements by KubeJS

## Quick Example

### KubeJS 6

```js
ServerEvents.advancement((event) => {
    const { BOUNDS, PREDICATE, TRIGGER } = event;

    // Define trigger
    const jump5times = TRIGGER.tick((triggerBuilder) =>
        triggerBuilder.addStat(Stats.JUMP, Stats.CUSTOM, BOUNDS.min$Integer(5)));
    const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
        triggerBuilder.setChild(PREDICATE.entity({
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
    
    // Remove an exist advancement
    event.remove("minecraft:story/lava_bucket");
    
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
```

# How to reload
Just use ```/reload```

# Features

## Custom trigger
- blockDestroyed: triggers when the player breaks a block.
- playerTouch: triggers when the player touch an entity.
- bossEvent: triggers when the player joins a boss fight.
- More idea...

## Custom reward
- addEffect: to give effect.
- More idea...

## Custom condition
- requireParentDone: check if parent done, else it will not be done

# TODO
- More non-vanilla triggers
- More than vanilla rewards
