package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class CustomTrigger extends AbstractCriterion<CustomTriggerInstance> {
    static final Identifier IMPOSSIBLE_ID = new Identifier("advjs", "impossible");
    private final Identifier ID;
    private final LinkedList<TriggerMatchCallback> callbacks;

    public CustomTrigger(Identifier id, LinkedList<TriggerMatchCallback> callbacks) {
        this.ID = id;
        this.callbacks = callbacks;
    }

    @Override
    protected @NotNull CustomTriggerInstance conditionsFromJson(@NotNull JsonObject jsonObject, @NotNull LootContextPredicate player, @NotNull AdvancementEntityPredicateDeserializer context) {
        return new CustomTriggerInstance(ID, player, new LinkedList<>());
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity serverPlayer, Object... tests) {
        if (ID == IMPOSSIBLE_ID) return;
        this.trigger(serverPlayer, instance -> instance.matches(tests));
    }

    @HideFromJS
    public CustomTriggerInstance create() {
        return new CustomTriggerInstance(ID, LootContextPredicate.EMPTY, callbacks);
    }

    @HideFromJS
    public CustomTriggerInstance create(LootContextPredicate player) {
        return new CustomTriggerInstance(ID, player, callbacks);
    }
}
