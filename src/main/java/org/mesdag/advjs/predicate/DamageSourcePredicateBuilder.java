package org.mesdag.advjs.predicate;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.function.Consumer;

public class DamageSourcePredicateBuilder {
    final DamageSourcePredicate.Builder builder = new DamageSourcePredicate.Builder();

    @Info(value = "Damage type tag that the type of the damage should be included in.",
        params = {
            @Param(name = "tagName"),
            @Param(name = "expected")
        })
    public void tag(ResourceLocation tagName, boolean expected) {
        builder.tag(new TagPredicate<>(TagKey.create(Registries.DAMAGE_TYPE, tagName), expected));
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(EntityPredicate direct) {
        builder.direct(direct);
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.direct(builder1.build());
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void source(EntityPredicate source) {
        builder.source(source);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void source(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.source(builder1.build());
    }

    @HideFromJS
    public DamageSourcePredicate build() {
        return builder.build();
    }
}
