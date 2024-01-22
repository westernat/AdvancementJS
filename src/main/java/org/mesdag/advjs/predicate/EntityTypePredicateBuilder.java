package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class EntityTypePredicateBuilder {
    EntityTypePredicate predicate = EntityTypePredicate.ANY;

    @Info("Accepts an entity ID.")
    public void setType(EntityType<?> entityType) {
        this.predicate = EntityTypePredicate.create(entityType);
    }

    @Info("Accepts an entity tag.")
    public void setTag(Identifier tag) {
        this.predicate = EntityTypePredicate.create(TagKey.of(RegistryKeys.ENTITY_TYPE, tag));
    }
}
