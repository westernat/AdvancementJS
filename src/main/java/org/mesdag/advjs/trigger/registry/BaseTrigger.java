package org.mesdag.advjs.trigger.registry;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class BaseTrigger extends SimpleCriterionTrigger<BaseTriggerInstance> {
    private final ResourceLocation ID;
    private final LinkedList<TriggerMatchCallback> callbacks;

    public BaseTrigger(ResourceLocation id, LinkedList<TriggerMatchCallback> callbacks) {
        this.ID = id;
        this.callbacks = callbacks;
    }

    @Override
    protected @NotNull BaseTriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate player, @NotNull DeserializationContext context) {
        return new BaseTriggerInstance(ID, player, new LinkedList<>());
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayer, Object... tests) {
        this.trigger(serverPlayer, instance -> instance.matches(tests));
    }

    @HideFromJS
    public BaseTriggerInstance create() {
        return new BaseTriggerInstance(ID, ContextAwarePredicate.ANY, callbacks);
    }

    @HideFromJS
    public BaseTriggerInstance create(ContextAwarePredicate player) {
        return new BaseTriggerInstance(ID, player, callbacks);
    }
}
