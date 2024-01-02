package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

public class ChanneledLightningBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite[] victims = new EntityPredicate.Composite[]{EntityPredicate.Composite.ANY};

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictims(EntityPredicate... entities) {
        this.victims = Stream.of(entities).map(EntityPredicate.Composite::wrap).toArray(EntityPredicate.Composite[]::new);
    }

    @Info("""
        The victims hit by the lightning summoned by the Channeling enchantment.
                
        All entities in this list must be hit.
        """)
    public void setVictims(EntityType<?>... entityTypes) {
        this.victims = Stream.of(entityTypes).map(this::warpEntity).toArray(EntityPredicate.Composite[]::new);
    }
}
