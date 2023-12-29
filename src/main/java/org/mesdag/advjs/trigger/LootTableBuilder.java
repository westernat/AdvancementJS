package org.mesdag.advjs.trigger;

import net.minecraft.util.Identifier;

class LootTableBuilder extends AbstractTriggerBuilder {
    Identifier lootTable = new Identifier("minecraft:chests/bastion_other");

    public void setLootTable(Identifier lootTable) {
        this.lootTable = lootTable;
    }
}
