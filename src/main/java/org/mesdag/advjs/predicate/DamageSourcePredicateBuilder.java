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
import net.minecraft.world.entity.EntityType;
import org.mesdag.advjs.util.EntitySetter;

import java.util.function.Consumer;

public class DamageSourcePredicateBuilder implements EntitySetter {
    final DamageSourcePredicate.Builder builder = new DamageSourcePredicate.Builder();

    @Info(value = "Damage type tag that the type of the damage should be included in.",
        params = {
            @Param(name = "tagName"),
            @Param(name = "expected")
        })
    public void tag(ResourceLocation tagName, boolean expected) {
        builder.tag(new TagPredicate<>(TagKey.create(Registries.DAMAGE_TYPE, tagName), expected));
    }

    @Info(value = "Damage type tag that the type of the damage should be included in. Excepted is true.",
        params = @Param(name = "tagName"))
    public void tag(ResourceLocation tagName) {
        builder.tag(new TagPredicate<>(TagKey.create(Registries.DAMAGE_TYPE, tagName), true));
    }

    @Info("The entity that was the direct cause of the damage.")
    public void directByPredicate(EntityPredicate direct) {
        builder.direct(direct);
    }

    @Info("The entity that was the direct cause of the damage.")
    public void directByType(EntityType<?> direct) {
        builder.direct(toEntity(direct));
    }

    @Info("The entity that was the direct cause of the damage.")
    public void direct(Consumer<EntityPredicateBuilder> consumer) {
        EntityPredicateBuilder builder1 = new EntityPredicateBuilder();
        consumer.accept(builder1);
        builder.direct(builder1.build());
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void sourceByPredicate(EntityPredicate source) {
        builder.source(source);
    }

    @Info("Checks the entity that was the source of the damage (for example: The skeleton that shot the arrow).")
    public void sourceByType(EntityType<?> source) {
        builder.source(toEntity(source));
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
