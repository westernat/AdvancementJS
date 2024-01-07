package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.GETTER_MAP;

public class AdvGetter {
    private final ResourceLocation savePath;
    private Consumer<DisplayBuilder> displayConsumer;
    private Consumer<RewardsBuilder> rewardsConsumer;
    private Consumer<CriteriaBuilder> criteriaConsumer;

    @HideFromJS
    public AdvGetter(ResourceLocation savePath) {
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

    @HideFromJS
    public Consumer<DisplayBuilder> getDisplayConsumer() {
        return displayConsumer;
    }

    @HideFromJS
    public Consumer<CriteriaBuilder> getCriteriaConsumer() {
        return criteriaConsumer;
    }

    @HideFromJS
    public Consumer<RewardsBuilder> getRewardsConsumer() {
        return rewardsConsumer;
    }
}
