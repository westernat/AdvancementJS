package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;
import org.mesdag.advjs.predicate.DamageSourcePredicateBuilder;
import org.mesdag.advjs.predicate.EntityPredicateBuilder;
import org.mesdag.advjs.predicate.condition.ICondition;
import org.mesdag.advjs.trigger.AbstractTriggerBuilder;
import org.mesdag.advjs.util.Bounds;

import java.util.function.Consumer;

public class IncreasedKillScoreTrigger extends SimpleCriterionTrigger<IncreasedKillScoreTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("advjs:increased_kill_score");

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate playerPredicate, @NotNull DeserializationContext deserializationContext) {
        ContextAwarePredicate killedPredicate = EntityPredicate.fromJson(jsonObject, "entity", deserializationContext);
        MinMaxBounds.Ints scoreBounds = MinMaxBounds.Ints.fromJson(jsonObject.get("score"));
        DamageSourcePredicate damageSourcePredicate = DamageSourcePredicate.fromJson(jsonObject.get("killing_below"));
        return new TriggerInstance(playerPredicate, killedPredicate, scoreBounds, damageSourcePredicate);
    }

    public void trigger(ServerPlayer serverPlayer, Entity entity, int score, DamageSource damageSource) {
        LootContext killed = EntityPredicate.createContext(serverPlayer, entity);
        this.trigger(serverPlayer, instance -> instance.matches(serverPlayer, killed, score, damageSource));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public static TriggerInstance create(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new TriggerInstance(builder.player, builder.killedPredicate, builder.scoreBounds, builder.damageSourcePredicate);
    }

    public static class Builder extends AbstractTriggerBuilder {
        ContextAwarePredicate killedPredicate = ContextAwarePredicate.ANY;
        MinMaxBounds.Ints scoreBounds = MinMaxBounds.Ints.ANY;
        DamageSourcePredicate damageSourcePredicate = DamageSourcePredicate.ANY;

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

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate killedPredicate;
        private final MinMaxBounds.Ints scoreBounds;
        private final DamageSourcePredicate damageSourcePredicate;

        public TriggerInstance(ContextAwarePredicate playerPredicate, ContextAwarePredicate killedPredicate, MinMaxBounds.Ints scoreBounds, DamageSourcePredicate damageSourcePredicate) {
            super(ID, playerPredicate);
            this.killedPredicate = killedPredicate;
            this.scoreBounds = scoreBounds;
            this.damageSourcePredicate = damageSourcePredicate;
        }

        public boolean matches(ServerPlayer serverPlayer, LootContext killed, int score, DamageSource damageSource) {
            return killedPredicate.matches(killed) && scoreBounds.matches(score) && damageSourcePredicate.matches(serverPlayer, damageSource);
        }
    }
}
