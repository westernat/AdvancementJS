package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;

import javax.annotation.Nullable;

@SuppressWarnings({"deprecation", "unused"})
public class BrewedPotionBuilder extends BaseTriggerInstanceBuilder {
    @Nullable Potion potion;

    @Info("A brewed potion ID.")
    public void setPotion(ResourceLocation name) {
        this.potion = BuiltInRegistries.POTION.get(name);
    }
}
