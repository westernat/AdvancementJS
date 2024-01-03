package org.mesdag.advjs.adv;

import net.minecraft.resources.ResourceLocation;

import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.GETTER_MAP;

public class AdvGetter {
    private final ResourceLocation path;
    private Consumer<DisplayBuilder> displayConsumer;
    private Consumer<RewardsBuilder> rewardsConsumer;
    private Consumer<CriteriaBuilder> criteriaConsumer;

    public AdvGetter(ResourceLocation path) {
        this.path = path;
        this.displayConsumer = displayBuilder -> {
        };
        this.rewardsConsumer = rewardsBuilder -> {
        };
        this.criteriaConsumer = criteriaBuilder -> {
        };
    }

    public void modifyDisplay(Consumer<DisplayBuilder> consumer) {
        this.displayConsumer = consumer;
        GETTER_MAP.put(path, this);
    }

    public void modifyCriteria(Consumer<CriteriaBuilder> consumer) {
        this.criteriaConsumer = consumer;
        GETTER_MAP.put(path, this);
    }

    public void modifyRewards(Consumer<RewardsBuilder> consumer) {
        this.rewardsConsumer = consumer;
        GETTER_MAP.put(path, this);
    }

    public Consumer<DisplayBuilder> getDisplayConsumer() {
        return displayConsumer;
    }

    public Consumer<CriteriaBuilder> getCriteriaConsumer() {
        return criteriaConsumer;
    }

    public Consumer<RewardsBuilder> getRewardsConsumer() {
        return rewardsConsumer;
    }
}
