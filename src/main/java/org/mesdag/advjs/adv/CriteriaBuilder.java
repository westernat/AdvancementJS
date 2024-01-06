package org.mesdag.advjs.adv;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.CriterionMerger;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
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

    public CriteriaBuilder(Map<String, AdvancementCriterion> criteria, JsonArray requirements) {
        this.criteria = criteria;
        this.strategy = CriterionMerger.AND;
        setRequirements(requirements);
    }

    public <Trigger extends AbstractCriterionConditions> void add(String name, Trigger trigger) {
        criteria.put(name, new AdvancementCriterion(trigger));
    }

    public <Trigger extends AbstractCriterionConditions> void add(Trigger trigger) {
        criteria.put(UUID.randomUUID().toString(), new AdvancementCriterion(trigger));
    }

    public void remove(String name) {
        criteria.remove(name);
    }

    public Map<String, AdvancementCriterion> getCriteria() {
        if (criteria.isEmpty()) {
            criteria.put("default", new AdvancementCriterion(InventoryChangedCriterion.Conditions.items(Items.APPLE)));
        }
        return criteria;
    }

    public void setStrategy(CriterionMerger strategy) {
        this.strategy = strategy;
    }

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
