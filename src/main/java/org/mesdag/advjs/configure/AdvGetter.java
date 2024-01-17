package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.configure.Data.GETTER_MAP;
import static org.mesdag.advjs.configure.Data.REQUIRE_DONE;

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
        update();
        return this;
    }

    @Info("Modify the criteria. Defaults to original criteria.")
    public AdvGetter modifyCriteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        this.criteriaConsumer = criteriaBuilderConsumer;
        update();
        return this;
    }

    @Info("Modify the rewards. Defaults to original rewards.")
    public AdvGetter modifyRewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        this.rewardsConsumer = rewardsBuilderConsumer;
        update();
        return this;
    }

    @Info("Add a nameless child to this advancement just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, UUID.randomUUID().toString(), getRootPath(savePath), true);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(savePath, name, getRootPath(savePath), false);
        advBuilderConsumer.accept(child);
        return child;
    }

    private static ResourceLocation getRootPath(ResourceLocation savePath) {
        String[] paths = savePath.getPath().split("/");
        String path;
        if (paths.length == 1) {
            path = paths[0];
        } else {
            path = String.join("/", Arrays.copyOfRange(paths, 0, paths.length - 1));
        }
        return new ResourceLocation(savePath.getNamespace(), path);
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvGetter requireParentDone() {
        REQUIRE_DONE.add(savePath);
        return this;
    }

    private void update() {
        GETTER_MAP.put(savePath, this);
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
