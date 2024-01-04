# AdvancementJS

Configure Advancements by KubeJS

## Quick Example

### KubeJS 6

```js
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
    event.remove("minecraft:story/lava_bucket");
    
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
```
