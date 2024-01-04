package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;


class ChangeDimensionBuilder extends AbstractTriggerBuilder {
    ResourceKey<Level> from = null;
    ResourceKey<Level> to = null;

    @Info("The dimension the entity traveled from.")
    public void setFrom(ResourceLocation from) {
        this.from = ResourceKey.create(Registries.DIMENSION, from);
    }

    @Info("The dimension the entity traveled to.")
    public void setTo(ResourceLocation to) {
        this.to = ResourceKey.create(Registries.DIMENSION, to);
    }
}
