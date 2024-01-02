# AdvancementJS

Configure Advancements by KubeJS

## Quick Exmaple

### KubeJS 6

```js
ServerEvents.advancement((event) => {
    const { BOUNDS, PREDICATE, TRIGGER } = event;

    // 定义触发器
    const jump5times = TRIGGER.tick((triggerBuilder) =>
        triggerBuilder.addStat(Stats.JUMP, Stats.CUSTOM, BOUNDS.min$Integer(5)));
    const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
        triggerBuilder.setChild(PREDICATE.entity({
            stepping_on: {
                dimension: "the_nether"
            }
        }))
    });

    // 创建根进度
    const root = event.create("advjs:hell")
        .display((displayBuilder) => {
            displayBuilder.setTitle("AdvancementJS")
            displayBuilder.setDescription("Quick example")
            displayBuilder.setIcon("diamond")
            displayBuilder.setAnnounceToChat(true)
        })
        .criteria((criteriaBuilder) => criteriaBuilder.add("tick", TRIGGER.tick()));

    // 为根进度添加子进度
    root.addChild("child1", (childBuilder) => {
        childBuilder
            .display((displayBuilder) => {
                displayBuilder.setTitle(Text.red("Holy"))
                displayBuilder.setDescription(Text.red("Hell starts"))
            })
            .criteria((criteriaBuilder) => {
                // 'OR'表示以下两个触发器任意一个达成, 则进度完成
                criteriaBuilder.setStrategy(RequirementsStrategy.OR)
                criteriaBuilder.add("bred", bred_in_nether)
                criteriaBuilder.add("jump", jump5times)
            })
            .rewards((rewardsBuilder) => {
                rewardsBuilder.setRecipes("minecraft:lodestone", "minecraft:brewing_stand")
                rewardsBuilder.setExperience(100)
            })
    });
})
```
