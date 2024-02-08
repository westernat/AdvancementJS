package org.mesdag.advjs.trigger;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;

public class LootTableBuilder extends BaseTriggerInstanceBuilder {
    ResourceLocation lootTable = new ResourceLocation("minecraft:chests/bastion_other");

    @Info("The resource location of the generated loot table.")
    public void setLootTable(ResourceLocation lootTable) {
        this.lootTable = lootTable;
    }
}
