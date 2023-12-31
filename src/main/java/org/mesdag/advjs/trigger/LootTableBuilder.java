package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;

class LootTableBuilder extends AbstractTriggerBuilder {
    ResourceLocation lootTable = new ResourceLocation("minecraft:chests/bastion_other");

    @Info("The resource location of the generated loot table.")
    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }
}
