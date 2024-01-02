package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;

import javax.annotation.Nullable;

class BrewedPotionBuilder extends AbstractTriggerBuilder {
    @Nullable
    Potion potion = null;

    @Info("A brewed potion ID.")
    public void setPotion(ResourceLocation name) {
        this.potion = Registry.POTION.get(name);
    }
}
