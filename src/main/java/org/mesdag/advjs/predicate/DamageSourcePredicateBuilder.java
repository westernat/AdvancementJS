package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class DamageSourcePredicateBuilder {
    final DamageSourcePredicate.Builder builder = new DamageSourcePredicate.Builder();

    @Info(value = "Damage type tag that the type of the damage should be included in.",
        params = {
            @Param(name = "tagName"),
            @Param(name = "expected")
        })
    public void tag(Identifier tagName, boolean expected) {
        builder.tag(new TagPredicate<>(TagKey.of(RegistryKeys.DAMAGE_TYPE, tagName), expected));
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(EntityPredicate direct) {
        builder.directEntity(direct);
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.directEntity(builder1.build());
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void source(EntityPredicate source) {
        builder.sourceEntity(source);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void source(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.sourceEntity(builder1.build());
    }

    @HideFromJS
    public DamageSourcePredicate build() {
        return builder.build();
    }
}
