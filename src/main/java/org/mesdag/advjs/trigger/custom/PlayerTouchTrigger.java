package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

public class PlayerTouchTrigger extends SimpleCriterionTrigger<PlayerTouchTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("advjs", "player_touch");

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate composite, @NotNull DeserializationContext deserializationContext) {
        ContextAwarePredicate touched = EntityPredicate.fromJson(jsonObject, "entity", deserializationContext);;
        return new TriggerInstance(composite, touched);
    }

    public void trigger(ServerPlayer serverPlayer, Entity entity) {
        LootContext lootcontext = EntityPredicate.createContext(serverPlayer, entity);
        this.trigger(serverPlayer, instance -> instance.matches(lootcontext));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate touched;

        public TriggerInstance(ContextAwarePredicate composite, ContextAwarePredicate touched) {
            super(ID, composite);
            this.touched = touched;
        }

        public boolean matches(LootContext context) {
            return touched.matches(context);
        }
    }
}
