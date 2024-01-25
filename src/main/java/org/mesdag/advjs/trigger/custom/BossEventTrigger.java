package org.mesdag.advjs.trigger.custom;

import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;
import org.mesdag.advjs.trigger.AbstractTriggerBuilder;

import java.util.function.Consumer;

public class BossEventTrigger extends SimpleCriterionTrigger<BossEventTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("advjs:boss_event");

    @Override
    public @NotNull TriggerInstance createInstance(@NotNull JsonObject jsonObject, @NotNull ContextAwarePredicate composite, @NotNull DeserializationContext deserializationContext) {
        boolean darkenScreen = jsonObject.has("darken_screen") && jsonObject.get("darken_screen").getAsBoolean();
        boolean playBossMusic = jsonObject.has("play_boss_music") && jsonObject.get("play_boss_music").getAsBoolean();
        boolean createWorldFog = jsonObject.has("create_world_fog") && jsonObject.get("create_world_fog").getAsBoolean();
        String key = jsonObject.has("key") ? jsonObject.get("key").getAsString() : "";
        return new TriggerInstance(composite, darkenScreen, playBossMusic, createWorldFog, key);
    }

    public void trigger(ServerPlayer serverPlayer, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog, String key) {
        this.trigger(serverPlayer, instance -> instance.matches(darkenScreen, playBossMusic, createWorldFog, key));
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public static TriggerInstance craete(Consumer<Builder> consumer) {
        Builder builder = new Builder();
        consumer.accept(builder);
        return new TriggerInstance(builder.player, builder.darkenScreen, builder.playBossMusic, builder.createWorldFog, builder.key);
    }

    public static class Builder extends AbstractTriggerBuilder {
        boolean darkenScreen = false;
        boolean playBossMusic = false;
        boolean createWorldFog = false;
        String key = "";

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

        @Info("""
            Match 'translation key' or 'literal text' of boss bar.
                        
            If set to "", it will not check. Defaults to "".
            """)
        public void setKey(String key) {
            this.key = key;
        }
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final boolean darkenScreen;
        private final boolean playBossMusic;
        private final boolean createWorldFog;
        private final String key;

        public TriggerInstance(ContextAwarePredicate composite, boolean darkenScreen, boolean playBossMusic, boolean createWorldFog, String key) {
            super(ID, composite);
            this.darkenScreen = darkenScreen;
            this.playBossMusic = playBossMusic;
            this.createWorldFog = createWorldFog;
            this.key = key;
        }

        public boolean matches(boolean darkenScreen, boolean playBossMusic, boolean createWorldFog, String key) {
            if (!this.key.equals("") && !this.key.equals(key)) {
                return false;
            } else {
                return this.darkenScreen == darkenScreen && this.playBossMusic && playBossMusic && this.createWorldFog == createWorldFog;
            }
        }
    }
}
