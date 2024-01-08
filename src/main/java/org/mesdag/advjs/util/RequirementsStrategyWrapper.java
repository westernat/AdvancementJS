package org.mesdag.advjs.util;

import net.minecraft.advancement.CriterionMerger;

public interface RequirementsStrategyWrapper {
    CriterionMerger AND = CriterionMerger.AND;
    CriterionMerger OR = CriterionMerger.OR;
    CriterionMerger and = CriterionMerger.AND;
    CriterionMerger or = CriterionMerger.OR;
}
