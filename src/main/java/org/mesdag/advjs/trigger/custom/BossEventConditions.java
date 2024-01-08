package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.mesdag.advjs.trigger.AbstractTriggerBuilder;

import java.util.function.Consumer;

public class BossEventConditions extends AbstractCriterion<BossEventConditions.Conditions> {
    static final Identifier ID = new Identifier("advjs:boss_event");

    @Override
    public @NotNull BossEventConditions.Conditions conditionsFromJson(@NotNull JsonObject jsonObject, @NotNull LootContextPredicate composite, @NotNull AdvancementEntityPredicateDeserializer deserializationContext) {
        boolean darkenScreen = jsonObject.has("darken_screen") && jsonObject.get("darken_screen").getAsBoolean();
        boolean playBossMusic = jsonObject.has("play_boss_music") && jsonObject.get("play_boss_music").getAsBoolean();
        boolean createWorldFog = jsonObject.has("create_world_fog") && jsonObject.get("create_world_fog").getAsBoolean();
        return new Conditions(composite, darkenScreen, playBossMusic, createWorldFog);
    }

    public void trigger(ServerPlayerEntity serverPlayer, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog) {
        this.trigger(serverPlayer, instance -> instance.matches(darkenScreen, playBossMusic, createWorldFog));
    }

    @Override
    public @NotNull Identifier getId() {
        return ID;
    }

    public static Conditions bossEvent(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new Conditions(builder.player, builder.darkenScreen, builder.playBossMusic, builder.createWorldFog);
    }

    public static class Builder extends AbstractTriggerBuilder {
        boolean darkenScreen = false;
        boolean playBossMusic = false;
        boolean createWorldFog = false;

        @Info("Check darken screen.")
        public void setDarkenScreen(boolean darkenScreen) {
            this.darkenScreen = darkenScreen;
        }

        @Info("Check play boss music.")
        public void setPlayBossMusic(boolean playBossMusic) {
            this.playBossMusic = playBossMusic;
        }

        @Info("Check create world fog.")
        public void setCreateWorldFog(boolean createWorldFog) {
            this.createWorldFog = createWorldFog;
        }
    }

    public static class Conditions extends AbstractCriterionConditions {
        private final boolean darkenScreen;
        private final boolean playBossMusic;
        private final boolean createWorldFog;

        public Conditions(LootContextPredicate composite, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog) {
            super(ID, composite);
            this.darkenScreen = darkenScreen;
            this.playBossMusic = playBossMusic;
            this.createWorldFog = createWorldFog;
        }

        public boolean matches(boolean darkenScreen, boolean playBossMusic, boolean createWorldFog) {
            return this.darkenScreen == darkenScreen && this.playBossMusic && playBossMusic && this.createWorldFog == createWorldFog;
        }
    }
}
