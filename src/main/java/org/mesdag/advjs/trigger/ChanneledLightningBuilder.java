package org.mesdag.advjs.trigger;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

public class ChanneledLightningBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Composite[] victims = new EntityPredicate.Composite[]{EntityPredicate.Composite.ANY};

    public void setVictims(EntityPredicate... entities) {
        this.victims = Stream.of(entities).map(EntityPredicate.Composite::wrap).toArray(EntityPredicate.Composite[]::new);
    }

    public void setVictims(ResourceLocation... entityIds) {
        this.victims = Stream.of(entityIds).map(this::warpEntity).toArray(EntityPredicate.Composite[]::new);
    }

    public void setVictims(EntityType<?>... entityTypes) {
        this.victims = Stream.of(entityTypes).map(this::warpEntity).toArray(EntityPredicate.Composite[]::new);
    }
}
