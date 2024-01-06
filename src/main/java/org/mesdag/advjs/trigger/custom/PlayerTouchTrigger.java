package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

public class PlayerTouchTrigger extends SimpleCriterionTrigger<PlayerTouchTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("advjs:player_touch");

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject jsonObject, EntityPredicate.@NotNull Composite composite, @NotNull DeserializationContext deserializationContext) {
        JsonElement entity = jsonObject.get("entity");
        EntityPredicate.Composite touched;
        if (entity.isJsonNull()) {
            touched = EntityPredicate.Composite.ANY;
        } else {
            touched = EntityPredicate.Composite.wrap(EntityPredicate.fromJson(entity));
        }
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
        private final EntityPredicate.Composite touched;

        public TriggerInstance(EntityPredicate.Composite composite, EntityPredicate.Composite touched) {
            super(ID, composite);
            this.touched = touched;
        }

        public boolean matches(LootContext context) {
            return touched.matches(context);
        }
    }
}
