package org.mesdag.advjs.advancement;

import com.google.common.collect.Maps;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, AdvancementCriterion> criteria;
    private CriterionMerger strategy = CriterionMerger.AND;
    @Nullable
    private String[][] requirements;

    CriteriaBuilder() {
        this.criteria = Maps.newLinkedHashMap();
    }

    @HideFromJS
    public CriteriaBuilder(Map<String, AdvancementCriterion> criteria) {
        this.criteria = criteria;
    }

    @Info(value = "Add a named trigger for this advancement.",
        params = {
            @Param(name = "name", value = "The name of this trigger."),
            @Param(name = "trigger", value = "The trigger itself.")
        })
    public void add(String name, CriterionConditions instance) {
        criteria.put(name, new AdvancementCriterion(instance));
    }

    @Info("Add a nameless trigger for this advancement.")
    public void add(CriterionConditions instance) {
        criteria.put(UUID.randomUUID().toString(), new AdvancementCriterion(instance));
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
    public Map<String, AdvancementCriterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new AdvancementCriterion(InventoryChangedCriterion.Conditions.items(Items.APPLE)));
        }
        return criteria;
    }

    @Info("""
        Set the condition that how does this advancement be triggered.
                
        If set to 'RequirementsStrategy.OR', the requirements will looks like '[[a, b, c]]'.
                
        If set to 'RequirementsStrategy.AND', the requirements will looks like '[[a], [b], [c]]'.
                
        Defaults to 'RequirementsStrategy.AND'.
        """)
    public void setStrategy(CriterionMerger strategy) {
        this.strategy = strategy;
    }

    @Info("""
        Defines how these criteria are completed to grant the advancement.
        
        Directly configure requirements instead of strategy.
        """)
    public void setRequirements(String[][] requirements) {
        this.requirements = requirements;
    }

    @HideFromJS
    public String[][] getRequirements() {
        return requirements == null ? strategy.createRequirements(getCriteria().keySet()) : requirements;
    }
}
