# AdvancementJS

Configure Advancements by KubeJS

## Quick Exmaple

### KubeJS 6

```js
StartupEvents.advancement((event) => {
  const { TRIGGER, PREDICATE } = event;

  const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
    triggerBuilder.setChild(
      PREDICATE.entity({
        stepping_on: {
          dimension: "the_nether",
        },
      })
    );
  });

  const root = event
    .create("advjs:hell")
    .display((displayBuilder) => displayBuilder.setIcon("diamond"))
    .criteria((criteriaBuilder) => criteriaBuilder.add("tick", TRIGGER.tick()));

  root.addChild("child1", (childBuilder) => {
    childBuilder
      .display((displayBuilder) => {
        displayBuilder.setTitle(Text.red("Holy Shit"));
        displayBuilder.setDescription(Text.red("Hell starts"));
      })
      .criteria((criteriaBuilder) => {
        criteriaBuilder.add("bred_in_nether", bred_in_nether);
      })
      .rewards((rewardsBuilder) => {
        rewardsBuilder.setExperience(100);
      });
  });

  event.remove("minecraft:story/smelt_iron");
});
```
