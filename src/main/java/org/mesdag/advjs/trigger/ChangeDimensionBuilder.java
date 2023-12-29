package org.mesdag.advjs.trigger;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;


class ChangeDimensionBuilder extends AbstractTriggerBuilder {
    ResourceKey<Level> from = null;
    ResourceKey<Level> to = null;

    public void setFrom(ResourceLocation from) {
        this.from = ResourceKey.create(Registry.DIMENSION_REGISTRY, from);
    }

    public void setTo(ResourceLocation to) {
        this.to = ResourceKey.create(Registry.DIMENSION_REGISTRY, to);
    }
}
