package org.mesdag.advjs.trigger.registry;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class BaseCriterion extends AbstractCriterion<BaseTriggerInstance> {
    private final Identifier ID;
    private final LinkedList<TriggerMatchCallback> callbacks;

    public BaseCriterion(Identifier id, LinkedList<TriggerMatchCallback> callbacks) {
        this.ID = id;
        this.callbacks = callbacks;
    }

    @Override
    protected @NotNull BaseTriggerInstance conditionsFromJson(@NotNull JsonObject jsonObject, @NotNull LootContextPredicate player, @NotNull AdvancementEntityPredicateDeserializer context) {
        return new BaseTriggerInstance(ID, player, new LinkedList<>());
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity serverPlayer, Object... tests) {
        this.trigger(serverPlayer, instance -> instance.matches(tests));
    }

    @HideFromJS
    public BaseTriggerInstance create() {
        return new BaseTriggerInstance(ID, LootContextPredicate.EMPTY, callbacks);
    }

    @HideFromJS
    public BaseTriggerInstance create(LootContextPredicate player) {
        return new BaseTriggerInstance(ID, player, callbacks);
    }
}
