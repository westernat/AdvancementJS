package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityEffectPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import org.mesdag.advjs.util.EntitySetter;

class EffectsChangedBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityEffectPredicate effects = EntityEffectPredicate.EMPTY;
    LootContextPredicate source = LootContextPredicate.EMPTY;

    @Info("A list of active status effects the player currently has.")
    public void setEffects(EntityEffectPredicate effects) {
        this.effects = effects;
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSource(EntityPredicate source) {
        this.source = EntityPredicate.asLootContextPredicate(source);
    }

    @Info("""
        The entity that was the source of the status effect.
                
        When there is no entity or when the effect was self-applied or removed,
                
        the test passes only if the source is not specified.
        """)
    public void setSource(EntityType<?> entityType) {
        this.source = warpEntity(entityType);
    }
}
