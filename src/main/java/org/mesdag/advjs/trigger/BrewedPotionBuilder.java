package org.mesdag.advjs.trigger;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potion;

import javax.annotation.Nullable;

class BrewedPotionBuilder extends AbstractTriggerBuilder {
    @Nullable
    Potion potion = null;

    public void setPotion(ResourceLocation name) {
        this.potion = Registry.POTION.get(name);
    }
}
