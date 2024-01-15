package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

public class ChanneledLightningBuilder extends AbstractTriggerBuilder implements EntitySetter {
    ContextAwarePredicate[] victims = new ContextAwarePredicate[]{ContextAwarePredicate.ANY};

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictimsByPredicate(EntityPredicate... entities) {
        this.victims = Stream.of(entities).map(EntityPredicate::wrap).toArray(ContextAwarePredicate[]::new);
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictimsByType(EntityType<?>... entityTypes) {
        this.victims = Stream.of(entityTypes).map(this::warpEntity).toArray(ContextAwarePredicate[]::new);
    }
}
