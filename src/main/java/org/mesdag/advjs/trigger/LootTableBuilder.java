package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.util.Identifier;

public class LootTableBuilder extends BaseTriggerInstanceBuilder {
    Identifier lootTable = new Identifier("minecraft:chests/bastion_other");

    @Info("The resource location of the generated loot table.")
    public void setLootTable(Identifier lootTable) {
        this.lootTable = lootTable;
    }
}
