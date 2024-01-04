package org.mesdag.advjs.adv;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Items;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, Criterion> criteria;
    private RequirementsStrategy strategy;
    @Nullable
    private String[][] requirements;

    CriteriaBuilder() {
        this.criteria = Maps.newLinkedHashMap();
        this.strategy = RequirementsStrategy.AND;
        requirements = null;
    }

    public CriteriaBuilder(Map<String, Criterion> criteria, JsonArray requirements) {
        this.criteria = criteria;
        this.strategy = RequirementsStrategy.AND;
        setRequirements(requirements);
    }

    @Info(value = "Add a named trigger for this advancement.",
        params = {
            @Param(name = "name", value = "The name of this trigger"),
            @Param(name = "trigger", value = "The trigger itself")
        })
    public <Trigger extends AbstractCriterionTriggerInstance> void add(String name, Trigger trigger) {
        criteria.put(name, new Criterion(trigger));
    }

    @Info("Add a nameless trigger for this advancement.")
    public <Trigger extends AbstractCriterionTriggerInstance> void add(Trigger trigger) {
        criteria.put(UUID.randomUUID().toString(), new Criterion(trigger));
    }

    @Info("Remove a trigger from this advancement by its name")
    public void remove(String name) {
        criteria.remove(name);
    }

    public Map<String, Criterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new Criterion(InventoryChangeTrigger.TriggerInstance.hasItems(Items.APPLE)));
        }
        return criteria;
    }

    @Info("""
        Set the condition that how does this advancement be triggered.
                
        If set to 'RequirementsStrategy.OR', the requirements will looks like '[[a], [b], [c]]'.
                
        If set to 'RequirementsStrategy.AND', the requirements will looks like '[[a, b, c]]'.
                
        Defaults to 'RequirementsStrategy.AND'
        """)
    public void setStrategy(RequirementsStrategy strategy) {
        this.strategy = strategy;
    }

    @Info("""
        You should call 'setStrategy' method instead of 'setRequirements'.
                
        Unless you're really know what are you doing!
        """)
    public void setRequirements(JsonArray jsonArray) {
        String[][] astring = new String[jsonArray.size()][];

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonArray jsonArray1 = GsonHelper.convertToJsonArray(jsonArray.get(i), "requirements[" + i + "]");
            astring[i] = new String[jsonArray1.size()];

            for (int j = 0; j < jsonArray1.size(); ++j) {
                astring[i][j] = GsonHelper.convertToString(jsonArray1.get(j), "requirements[" + i + "][" + j + "]");
            }
        }

        this.requirements = astring;
    }

    public String[][] getRequirements() {
        Set<String> names = getCriteria().keySet();
        if (requirements == null) {
            return strategy.createRequirements(names);
        }
        for (String name : names) {
            for (String[] requirement : requirements) {
                if (!ArrayUtils.contains(requirement, name)) {
                    return strategy.createRequirements(names);
                }
            }
        }
        return requirements;
    }
}
