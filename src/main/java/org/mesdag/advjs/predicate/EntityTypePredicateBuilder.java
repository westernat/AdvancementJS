package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class EntityTypePredicateBuilder {
    EntityTypePredicate predicate = EntityTypePredicate.ANY;

    @Info("Accepts an entity ID.")
    public void setType(EntityType<?> entityType) {
        this.predicate = EntityTypePredicate.of(entityType);
    }

    @Info("Accepts an entity tag.")
    public void setTag(ResourceLocation tag) {
        this.predicate = EntityTypePredicate.of(TagKey.create(Registries.ENTITY_TYPE, tag));
    }
}
