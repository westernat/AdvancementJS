package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.trigger.BaseTriggerInstanceBuilder;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class IncreasedKillScoreCriterion extends AbstractCriterion<IncreasedKillScoreCriterion.Condition> {
    static final Identifier ID = new Identifier("advjs", "increased_kill_score");

    @Override
    public @NotNull IncreasedKillScoreCriterion.Condition conditionsFromJson(@NotNull JsonObject jsonObject, @NotNull LootContextPredicate playerPredicate, @NotNull AdvancementEntityPredicateDeserializer deserializationContext) {
        LootContextPredicate killedPredicate = EntityPredicate.contextPredicateFromJson(jsonObject, "entity", deserializationContext);
        NumberRange.IntRange scoreBounds = NumberRange.IntRange.fromJson(jsonObject.get("score"));
        DamageSourcePredicate damageSourcePredicate = DamageSourcePredicate.fromJson(jsonObject.get("killing_below"));
        return new Condition(playerPredicate, killedPredicate, scoreBounds, damageSourcePredicate);
    }

    public void trigger(ServerPlayerEntity serverPlayer, Entity entity, int score, DamageSource damageSource) {
        LootContext killed = EntityPredicate.createAdvancementEntityLootContext(serverPlayer, entity);
        this.trigger(serverPlayer, instance -> instance.matches(serverPlayer, killed, score, damageSource));
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public static Condition create(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new Condition(builder.player, builder.killedPredicate, builder.scoreBounds, builder.damageSourcePredicate);
    }

    public static class Builder extends BaseTriggerInstanceBuilder {
        LootContextPredicate killedPredicate = LootContextPredicate.EMPTY;
        NumberRange.IntRange scoreBounds = NumberRange.IntRange.ANY;
        DamageSourcePredicate damageSourcePredicate = DamageSourcePredicate.EMPTY;

        @Info("The entity that was killed.")
        public void setKilledByPredicate(EntityPredicate killedPredicate) {
            this.killedPredicate = wrapEntity(killedPredicate);
        }

        @Info("The entity that was killed.")
        public void setKilledByType(EntityType<?> entityType) {
            this.killedPredicate = wrapEntity(entityType);
        }

        @Info("The entity that was killed.")
        public void setKilled(Consumer<EntityPredicateBuilder> consumer) {
            EntityPredicateBuilder builder = new EntityPredicateBuilder();
            consumer.accept(builder);
            this.killedPredicate = wrapEntity(builder.build());
        }

        @Info("The entity that was killed.")
        public void setKilledByCondition(ICondition... conditions) {
            this.killedPredicate = wrapEntity(conditions);
        }

        @Info("The score that player increased.")
        public void setScore(Bounds bounds) {
            this.scoreBounds = bounds.toIntegerBounds();
        }

        @Info("The type of damage that killed an entity.")
        public void setKillingBelowByPredicate(DamageSourcePredicate damageSourcePredicate) {
            this.damageSourcePredicate = damageSourcePredicate;
        }

        @Info("The type of damage that killed an entity.")
        public void setKillingBelow(Consumer<DamageSourcePredicateBuilder> consumer) {
            DamageSourcePredicateBuilder builder = new DamageSourcePredicateBuilder();
            consumer.accept(builder);
            this.damageSourcePredicate = builder.build();
        }
    }

    public static class Condition extends AbstractCriterionConditions {
        private final LootContextPredicate killedPredicate;
        private final NumberRange.IntRange scoreBounds;
        private final DamageSourcePredicate damageSourcePredicate;

        public Condition(LootContextPredicate playerPredicate, LootContextPredicate killedPredicate, NumberRange.IntRange scoreBounds, DamageSourcePredicate damageSourcePredicate) {
            super(ID, playerPredicate);
            this.killedPredicate = killedPredicate;
            this.scoreBounds = scoreBounds;
            this.damageSourcePredicate = damageSourcePredicate;
        }

        public boolean matches(ServerPlayerEntity serverPlayer, LootContext killed, int score, DamageSource damageSource) {
            return killedPredicate.test(killed) && scoreBounds.test(score) && damageSourcePredicate.test(serverPlayer, damageSource);
        }
    }
}
