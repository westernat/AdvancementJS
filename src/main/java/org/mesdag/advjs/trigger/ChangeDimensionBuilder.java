package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;


class ChangeDimensionBuilder extends BaseTriggerInstanceBuilder {
    RegistryKey<World> from = null;
    RegistryKey<World> to = null;

    @Info("The dimension the entity traveled from.")
    public void setFrom(Identifier from) {
        this.from = RegistryKey.of(RegistryKeys.WORLD, from);
    }

    @Info("The dimension the entity traveled to.")
    public void setTo(Identifier to) {
        this.to = RegistryKey.of(RegistryKeys.WORLD, to);
    }
}
