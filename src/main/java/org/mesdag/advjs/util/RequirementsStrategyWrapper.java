package org.mesdag.advjs.util;

import net.minecraft.advancements.RequirementsStrategy;

public interface RequirementsStrategyWrapper {
    RequirementsStrategy AND = RequirementsStrategy.AND;
    RequirementsStrategy OR = RequirementsStrategy.OR;
    RequirementsStrategy and = RequirementsStrategy.AND;
    RequirementsStrategy or = RequirementsStrategy.OR;
}
