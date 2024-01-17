package org.mesdag.advjs.configure;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.CriterionTriggerInstance;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, Criterion> criteria;
    private RequirementsStrategy strategy = RequirementsStrategy.AND;
    @Nullable
    private String[][] requirements = null;

    CriteriaBuilder() {
        this.criteria = Maps.newLinkedHashMap();
    }

    @HideFromJS
    public CriteriaBuilder(Map<String, Criterion> criteria) {
        this.criteria = criteria;
    }

    @Info(value = "Add a named trigger for this advancement.",
        params = {
            @Param(name = "name", value = "The name of this trigger."),
            @Param(name = "trigger", value = "The trigger itself.")
        })
    public <Trigger extends CriterionTriggerInstance> void add(String name, Trigger trigger) {
        criteria.put(name, new Criterion(trigger));
    }

    @Info("Add a nameless trigger for this advancement.")
    public <Trigger extends CriterionTriggerInstance> void add(Trigger trigger) {
        criteria.put(UUID.randomUUID().toString(), new Criterion(trigger));
    }

    @Info("Remove a trigger from this advancement by its name.")
    public void remove(String name) {
        criteria.remove(name);
    }

    @Info("Clear all criterion.")
    public void clear() {
        criteria.clear();
    }

    @HideFromJS
    public Map<String, Criterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE)));
        }
        return criteria;
    }

    @Info("""
        Defines how these criteria are completed to grant the advancement.
                
        If set to 'RequirementsStrategy.OR', the requirements will looks like '[[a, b, c]]'.
                
        If set to 'RequirementsStrategy.AND', the requirements will looks like '[[a], [b], [c]]'.
                
        Defaults to 'RequirementsStrategy.AND'.
        """)
    public void setStrategy(RequirementsStrategy strategy) {
        this.strategy = strategy;
    }

    @Info("""
        Defines how these criteria are completed to grant the advancement.
        
        Directly configure requirements instead of strategy.
        """)
    public void setRequirements(String[][] requirements) {
        this.requirements = requirements;
    }

    public String[][] getRequirements() {
        return requirements == null ? strategy.createRequirements(getCriteria().keySet()) : requirements;
    }
}
