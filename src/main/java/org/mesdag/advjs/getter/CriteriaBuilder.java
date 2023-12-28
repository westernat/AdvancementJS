package org.mesdag.advjs.getter;

import com.google.common.collect.Maps;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;

import java.util.Map;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, AdvancementCriterion> criteria = Maps.newLinkedHashMap();
    private CriterionMerger requirementsStrategy = CriterionMerger.AND;


    public <Trigger extends AbstractCriterionConditions> void add(String name, Trigger instance) {
        criteria.put(name, new AdvancementCriterion(instance));
    }

    public <Trigger extends AbstractCriterionConditions> void add(Trigger instance) {
        criteria.put(UUID.randomUUID().toString(), new AdvancementCriterion(instance));
    }

    public Map<String, AdvancementCriterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new AdvancementCriterion(InventoryChangedCriterion.Conditions.items(Items.APPLE)));
        }
        return criteria;
    }

    public void setStrategy(CriterionMerger strategy) {
        this.requirementsStrategy = strategy;
    }

    public String[][] getRequirements() {
        return requirementsStrategy.createRequirements(getCriteria().keySet());
    }
}
