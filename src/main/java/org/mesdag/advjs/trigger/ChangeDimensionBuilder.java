package org.mesdag.advjs.trigger;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;


class ChangeDimensionBuilder extends AbstractTriggerBuilder {
    RegistryKey<World> from = null;
    RegistryKey<World> to = null;

    public void setFrom(Identifier from) {
        this.from = RegistryKey.of(Registry.WORLD_KEY, from);
    }

    public void setTo(Identifier to) {
        this.to = RegistryKey.of(Registry.WORLD_KEY, to);
    }
}
