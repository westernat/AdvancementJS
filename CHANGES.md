# 2.4.0
## New method
- requireOthersDone(requires[]: ResourceLocation...)
  - It will check if advancements that you put in had done.

## New feature
- Outputting more debugging information.
- More advancement warning message show in advancement.
- Banding Bounds to KubeJS
- New trigger: increasedKillScore
  - Triggers when the player killed an entity, it will triggerTest the score that player increased.

## Rename
- AdvBuilder.getSavePath() -> AdvBuilder.getId()

# 2.5.0
## Addition
- BlockDestroyedTrigger.Builder.ofTag(tag: ResourceLocation): void
- AdvConfigureEventJS.lock(toLock: Ingredient, lockBy: ResourceLocation): void
- AdvConfigureEventJS.lock(toLock: ItemPredicate, lockBy: ResourceLocation): void
- Trigger.fromJson(json: JsonObject): CriterionTriggerInstance

## Removal
- AdvConfigureEventJS.lock(toLock: ItemStack, lockBy: ResourceLocation): void
- AdvConfigureEventJS.lock(toLock: ItemStack, lockBy: AdvBuilder): void

## Fix
- Allow ```requireParentDone``` and ```requireOthersDone``` use in same advancement

# 2.6.0
## Compat
- ```Revelationary```(Fabric only), the new client event: ```AdvJSEvents.revelation```
- ```EventHorizon```(Forge only), the new client event: ```AdvJSEvents.revelation```

## Reconstruct
- ```ServerEvents.advancement``` -> ```AdvJSEvents.advancement```
- ```AdvConfigureEventJS.lock``` -> ```AdvJSEvents.lock```

## Addition
- The new startup event ```AdvJSEvents.trigger```, for registering custom trigger
  - Use ```CustomTriggers.of(id: ResourceLocation): BaseTrigger``` to get your custom trigger for triggers
  - Use ```Trigger.custom(id: ResourceLocation): BaseTriggerInstance``` to create a trigger instance for criteria

# 2.7.0
## Addition
- ```AdvBuilder.repeatable()``` and ```AdvGetter.repeatable()```
  - If invoked this method, the advancement will revoke after grant automatically.
- ```AdvGetter.changeParent(parentId: ResourceLocation)```
- PlayerPredicateBuilder.checkAdvancementCriterions(advancement: ResourceLocation, criterions: Map<String, Boolean>)

## Fix
- If an advancement has no display, the displayModifier will not throw error, but create a new display
- ```/advjs``` will generate correct code

## Rename
- ```AdvRemoveFilter``` -> ```AdvancementFilter```
- ```GameType``` -> ```GameMode```
- ```BaseTrigger``` -> ```CustomTrigger```

# 2.8.0
## Addition
- AdvLockEventJS.itemUse(toLock: Item, lockBy: ResourceLocation): void
- AdvLockEventJS.blockInteract(toLock: Block, lockBy: ResourceLocation): void
- AdvLockEventJS.entityInteract(toLock: EntityType\<any\>, lockBy: ResourceLocation): void

## Binding
- AdvHelper: for checking server player's advancement isn't done

## Upgrades
- AdvancementFilter: compound type is available

# 2.9.0
## Fix
- ```AdvancementFilter``` will filter icon correctly
- ```DamagePredicateBuilder.setType(predicate)``` -> ```DamagePredicateBuilder.setTypeByPredicate(predicate)```

## Addition
- Client example in ```/advjs```

## Compat
- ```Better Advancements```: the new server event: ```AdvJSEvents.betterAdv```

# 2.9.1
## Addition
- Send custom message to player who tried to interact with locked things
- ```AdvJSEvents.betterAdv``` support modify vanilla display
- ```BetterAdvModifier``` support more override method