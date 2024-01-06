package org.mesdag.advjs.adv;

import net.minecraft.resources.ResourceLocation;

import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.GETTER_MAP;

public class AdvGetter {
    private final ResourceLocation savePath;
    private Consumer<DisplayBuilder> displayConsumer;
    private Consumer<RewardsBuilder> rewardsConsumer;
    private Consumer<CriteriaBuilder> criteriaConsumer;

    public AdvGetter(ResourceLocation savePath) {
        this.savePath = savePath;
        this.displayConsumer = displayBuilder -> {
        };
        this.rewardsConsumer = rewardsBuilder -> {
        };
        this.criteriaConsumer = criteriaBuilder -> {
        };
    }

    public AdvGetter modifyDisplay(Consumer<DisplayBuilder> displayBuilderConsumer) {
        this.displayConsumer = displayBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    public AdvGetter modifyCriteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        this.criteriaConsumer = criteriaBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    public AdvGetter modifyRewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        this.rewardsConsumer = rewardsBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, UUID.randomUUID().toString(), savePath, true);
        advBuilderConsumer.accept(child);

        GETTER_MAP.put(savePath, this);
        return child;
    }

    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, name, savePath, false);
        advBuilderConsumer.accept(child);

        GETTER_MAP.put(savePath, this);
        return child;
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
