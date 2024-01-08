package org.mesdag.advjs.configure;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.util.JsonHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class CriteriaBuilder {
    private final Map<String, AdvancementCriterion> criteria;
    private CriterionMerger strategy;

    @Nullable
    private String[][] requirements;

    CriteriaBuilder() {
        this.criteria = Maps.newLinkedHashMap();
        this.strategy = CriterionMerger.AND;
        requirements = null;
    }

    @HideFromJS
    public CriteriaBuilder(Map<String, AdvancementCriterion> criteria, JsonArray requirements) {
        this.criteria = criteria;
        this.strategy = CriterionMerger.AND;
        setRequirements(requirements);
    }

    @Info(value = "Add a named trigger for this advancement.",
        params = {
            @Param(name = "name", value = "The name of this trigger"),
            @Param(name = "trigger", value = "The trigger itself")
        })
    public <Trigger extends CriterionConditions> void add(String name, Trigger instance) {
        criteria.put(name, new AdvancementCriterion(instance));
    }

    @Info("Add a nameless trigger for this advancement.")
    public <Trigger extends CriterionConditions> void add(Trigger instance) {
        criteria.put(UUID.randomUUID().toString(), new AdvancementCriterion(instance));
    }

    @Info("Remove a trigger from this advancement by its name")
    public void remove(String name) {
        criteria.remove(name);
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
                
        If set to 'RequirementsStrategy.OR', the requirements will looks like '[[a], [b], [c]]'.
                
        If set to 'RequirementsStrategy.AND', the requirements will looks like '[[a, b, c]]'.
                
        Defaults to 'RequirementsStrategy.AND'
        """)
    public void setStrategy(CriterionMerger strategy) {
        this.strategy = strategy;
    }

    @Info("""
        You should call 'setStrategy' method instead of 'setRequirements'.
                
        Unless you're really know what are you doing!
        """)
    public void setRequirements(JsonArray jsonArray) {
        String[][] astring = new String[jsonArray.size()][];

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonArray jsonArray1 = JsonHelper.asArray(jsonArray.get(i), "requirements[" + i + "]");
            astring[i] = new String[jsonArray1.size()];

            for (int j = 0; j < jsonArray1.size(); ++j) {
                astring[i][j] = JsonHelper.asString(jsonArray1.get(j), "requirements[" + i + "][" + j + "]");
            }
        }

        this.requirements = astring;
    }

    @HideFromJS
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
