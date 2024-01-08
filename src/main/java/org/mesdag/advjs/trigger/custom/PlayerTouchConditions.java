package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.entity.Entity;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class PlayerTouchConditions extends AbstractCriterion<PlayerTouchConditions.Conditions> {
    static final Identifier ID = new Identifier("advjs:player_touch");

    @Override
    public @NotNull PlayerTouchConditions.Conditions conditionsFromJson(@NotNull JsonObject jsonObject, EntityPredicate.@NotNull Extended composite, @NotNull AdvancementEntityPredicateDeserializer deserializationContext) {
        JsonElement entity = jsonObject.get("entity");
        EntityPredicate.Extended touched;
        if (entity.isJsonNull()) {
            touched = EntityPredicate.Extended.EMPTY;
        } else {
            touched = EntityPredicate.Extended.ofLegacy(EntityPredicate.fromJson(entity));
        }
        return new Conditions(composite, touched);
    }

    public void trigger(ServerPlayerEntity serverPlayer, Entity entity) {
        LootContext lootcontext = EntityPredicate.createAdvancementEntityLootContext(serverPlayer, entity);
        this.trigger(serverPlayer, instance -> instance.matches(lootcontext));
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final EntityPredicate.Extended touched;

        public Conditions(EntityPredicate.Extended composite, EntityPredicate.Extended touched) {
            super(ID, composite);
            this.touched = touched;
        }

        public boolean matches(LootContext context) {
            return touched.test(context);
        }
    }
}
