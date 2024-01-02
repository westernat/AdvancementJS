package org.mesdag.advjs.adv;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.GETTER_MAP;

public class AdvGetter {
    private final ResourceLocation path;
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();

    public AdvGetter(ResourceLocation path) {
        this.path = path;
    }

    public void modifyDisplay(Consumer<DisplayBuilder> consumer) {
        consumer.accept(displayBuilder);
        GETTER_MAP.put(path, this);
    }

    public void modifyCriteria(Consumer<CriteriaBuilder> consumer) {
        consumer.accept(criteriaBuilder);
        GETTER_MAP.put(path, this);
    }

    public void modifyRewards(Consumer<RewardsBuilder> consumer) {
        consumer.accept(rewardsBuilder);
        GETTER_MAP.put(path, this);
    }

    public DisplayInfo getDisplayInfo() {
        return displayBuilder.build();
    }

    public Map<String, Criterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    public AdvancementRewards getRewards() {
        return rewardsBuilder.build();
    }
}
