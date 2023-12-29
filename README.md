# AdvancementJS

Configure Advancements by KubeJS

## A simple example

### KubeJS 5

```js
onEvent('advjs', (event) => {
  const { TRIGGER, PREDICATE } = event;

  const bred_in_nether = TRIGGER.bredAnimals((triggerBuilder) => {
    triggerBuilder.setChild(PREDICATE.entity({
      stepping_on: {
        dimension: 'the_nether'
      }
    }))
  })

  const root = event.create('advjs:hell')
    .display((displayBuilder) => displayBuilder.setIcon('diamond'))
    .criteria((criteriaBuilder) => criteriaBuilder.add('tick', TRIGGER.tick()))

  root.addChild('child1', (childBuilder) => {
    childBuilder
      .display((displayBuilder) => {
        displayBuilder.setTitle(Text.red('Holy Shit'))
        displayBuilder.setDescription(Text.red('地狱开局'))
      })
      .criteria((criteriaBuilder) => {
        criteriaBuilder.add('bred_in_nether', bred_in_nether)
      })
      .rewards((rewardsBuilder) => {
        rewardsBuilder.setExperience(100)
      })
  })

  event.remove("minecraft:story/smelt_iron")
})
```
