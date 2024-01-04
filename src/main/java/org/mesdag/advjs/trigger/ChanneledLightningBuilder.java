package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

public class ChanneledLightningBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended[] victims = new EntityPredicate.Extended[]{EntityPredicate.Extended.EMPTY};

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictims(EntityPredicate... entities) {
        this.victims = Stream.of(entities).map(EntityPredicate.Extended::ofLegacy).toArray(EntityPredicate.Extended[]::new);
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictims(EntityType<?>... entityTypes) {
        this.victims = Stream.of(entityTypes).map(this::warpEntity).toArray(EntityPredicate.Extended[]::new);
    }
}
