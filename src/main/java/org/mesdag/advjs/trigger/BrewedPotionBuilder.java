package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public class BrewedPotionBuilder extends AbstractTriggerBuilder {
    @Nullable
    Potion potion = null;

    @Info("A brewed potion ID.")
    public void setPotion(Identifier name) {
        this.potion = Registry.POTION.get(name);
    }
}
