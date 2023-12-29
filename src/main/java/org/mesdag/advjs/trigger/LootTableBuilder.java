package org.mesdag.advjs.trigger;

import net.minecraft.resources.ResourceLocation;

class LootTableBuilder extends AbstractTriggerBuilder {
    ResourceLocation lootTable = new ResourceLocation("minecraft:chests/bastion_other");

    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }
}
