package org.mesdag.advjs.trigger;

import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

class BrewedPotionBuilder extends AbstractTriggerBuilder {
    @Nullable
    Potion potion = null;

    public void setPotion(Identifier name) {
        this.potion = Registry.POTION.get(name);
    }
}
