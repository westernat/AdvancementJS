package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;


class ChangeDimensionBuilder extends AbstractTriggerBuilder {
    RegistryKey<World> from = null;
    RegistryKey<World> to = null;

    @Info("The dimension the entity traveled from.")
    public void setFrom(Identifier from) {
        this.from = RegistryKey.of(Registry.WORLD_KEY, from);
    }

    @Info("The dimension the entity traveled to.")
    public void setTo(Identifier to) {
        this.to = RegistryKey.of(Registry.WORLD_KEY, to);
    }
}
