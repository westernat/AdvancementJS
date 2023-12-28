package org.mesdag.advjs.getter;

import com.google.common.collect.Maps;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Items;

import java.util.Map;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, Criterion> criteria = Maps.newLinkedHashMap();
    private RequirementsStrategy requirementsStrategy = RequirementsStrategy.AND;


    public <Trigger extends AbstractCriterionTriggerInstance> void add(String name, Trigger instance) {
        criteria.put(name, new Criterion(instance));
    }

    public <Trigger extends AbstractCriterionTriggerInstance> void add(Trigger instance) {
        criteria.put(UUID.randomUUID().toString(), new Criterion(instance));
    }

    public Map<String, Criterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE)));
        }
        return criteria;
    }

    public void setStrategy(RequirementsStrategy strategy) {
        this.requirementsStrategy = strategy;
    }

    public String[][] getRequirements() {
        return requirementsStrategy.createRequirements(getCriteria().keySet());
    }
}
