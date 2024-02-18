package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class CustomTrigger extends SimpleCriterionTrigger<CustomTriggerInstance> {
    static final ResourceLocation IMPOSSIBLE_ID = new ResourceLocation("advjs", "impossible");
    private final ResourceLocation ID;
    private final LinkedList<TriggerMatchCallback> callbacks;

    public CustomTrigger(ResourceLocation id, LinkedList<TriggerMatchCallback> callbacks) {
        this.ID = id;
        this.callbacks = callbacks;
    }

    @Override
    protected @NotNull CustomTriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate player, @NotNull DeserializationContext context) {
        // TODO JsonArray array = GsonHelper.getAsJsonArray(jsonObject, "matches", new JsonArray());
        return new CustomTriggerInstance(ID, player, new LinkedList<>());
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer serverPlayer, Object... tests) {
        if (ID == IMPOSSIBLE_ID) return;
        this.trigger(serverPlayer, instance -> instance.matches(tests));
    }

    @HideFromJS
    public CustomTriggerInstance create() {
        return new CustomTriggerInstance(ID, ContextAwarePredicate.ANY, callbacks);
    }

    @HideFromJS
    public CustomTriggerInstance create(ContextAwarePredicate player) {
        return new CustomTriggerInstance(ID, player, callbacks);
    }
}
