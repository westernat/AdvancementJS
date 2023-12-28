package org.mesdag.advjs.trigger;

import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.EntitySetter;

import java.util.stream.Stream;

public class ChanneledLightningBuilder extends AbstractTriggerBuilder implements EntitySetter {
    EntityPredicate.Extended[] victims = new EntityPredicate.Extended[]{EntityPredicate.Extended.EMPTY};

    public void setVictims(EntityPredicate... entities) {
        this.victims = Stream.of(entities).map(EntityPredicate.Extended::ofLegacy).toArray(EntityPredicate.Extended[]::new);
    }

    public void setVictims(Identifier... entityIds) {
        this.victims = Stream.of(entityIds).map(this::warpEntity).toArray(EntityPredicate.Extended[]::new);
    }

    public void setVictims(EntityType<?>... entityTypes) {
        this.victims = Stream.of(entityTypes).map(this::warpEntity).toArray(EntityPredicate.Extended[]::new);
    }
}
