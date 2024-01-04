package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.util.Identifier;

import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.GETTER_MAP;

public class AdvGetter {
    private final Identifier savePath;
    private Consumer<DisplayBuilder> displayConsumer;
    private Consumer<RewardsBuilder> rewardsConsumer;
    private Consumer<CriteriaBuilder> criteriaConsumer;

    public AdvGetter(Identifier savePath) {
        this.savePath = savePath;
        this.displayConsumer = displayBuilder -> {
        };
        this.rewardsConsumer = rewardsBuilder -> {
        };
        this.criteriaConsumer = criteriaBuilder -> {
        };
    }

    @Info("Modify the display. Defaults to original display.")
    public AdvGetter modifyDisplay(Consumer<DisplayBuilder> displayBuilderConsumer) {
        this.displayConsumer = displayBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    @Info("Modify the criteria. Defaults to original criteria.")
    public AdvGetter modifyCriteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        this.criteriaConsumer = criteriaBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    @Info("Modify the rewards. Defaults to original rewards.")
    public AdvGetter modifyRewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        this.rewardsConsumer = rewardsBuilderConsumer;

        GETTER_MAP.put(savePath, this);
        return this;
    }

    @Info("Add a nameless child to this advancement just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, UUID.randomUUID().toString(), savePath, true);
        advBuilderConsumer.accept(child);

        GETTER_MAP.put(savePath, this);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, name, savePath, false);
        advBuilderConsumer.accept(child);

        GETTER_MAP.put(savePath, this);
        return child;
    }

    @Info("Get display builder consumer that you just modified.")
    public Consumer<DisplayBuilder> getDisplayConsumer() {
        return displayConsumer;
    }

    @Info("Get criteria builder consumer that you just modified.")
    public Consumer<CriteriaBuilder> getCriteriaConsumer() {
        return criteriaConsumer;
    }

    @Info("Get rewards builder consumer that you just modified.")
    public Consumer<RewardsBuilder> getRewardsConsumer() {
        return rewardsConsumer;
    }
}
