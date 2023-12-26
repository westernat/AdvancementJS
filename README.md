# Advancement JS
Configure Advancements by JavaScript
## A simple example
```
onEvent('advjs', event => {
	let bred_in_nether = event.TRIGGER.bredAnimals(builder => {
		builder.setChild(event.PREDICATE.entityPredicate({
			stepping_on: {
				dimension: 'the_nether'
			}
		}))
	})

	let root = event.create('advjs:hell')
		.display(builder => builder.setIcon('diamond'))
		.criteria(builder => builder.add('tick', event.TRIGGER.tick()))

	root.addChild('child1', builder => {
		builder
			.display(builder => {
				builder.setTitle(Text.red('Holy Shit'))
				builder.setDescription(Text.red('地狱开局'))
			})
			.criteria(builder => builder.add('bred_in_nether', bred_in_nether))
			.rewards(builder => builder.setExperience(114514))
	})

	event.remove("minecraft:story/smelt_iron")
})
```
