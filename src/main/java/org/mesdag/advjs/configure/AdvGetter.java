package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.util.DisplayOffset;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.*;

public class AdvGetter {
    private final Identifier id;
    private Consumer<DisplayBuilder> displayConsumer;
    private Consumer<RewardsBuilder> rewardsConsumer;
    private Consumer<CriteriaBuilder> criteriaConsumer;

    @HideFromJS
    public AdvGetter(Identifier id) {
        this.id = id;
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
        AdvBuilder child = new AdvBuilder(id, UUID.randomUUID().toString(), getRootPath(id), AdvBuilder.WarnType.NONE);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, name, getRootPath(id), AdvBuilder.WarnType.NAMELESS);
        advBuilderConsumer.accept(child);
        return child;
    }

    private static Identifier getRootPath(Identifier savePath) {
        String[] paths = savePath.getPath().split("/");
        String path;
        if (paths.length == 1) {
            path = paths[0];
        } else {
            path = String.join("/", Arrays.copyOfRange(paths, 0, paths.length - 1));
        }
        return new Identifier(savePath.getNamespace(), path);
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvGetter requireParentDone() {
        REQUIRE_DONE.put(id, new Identifier[0]);
        return this;
    }

    @Info("It will check if advancements that you put in had done.")
    public AdvGetter requireOthersDone(Identifier... requires) {
        REQUIRE_DONE.put(id, requires);
        return this;
    }

    @Info(value = "Configure this advancement's position",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display.")
        })
    public AdvGetter displayOffset(float x, float y) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, false));
        return this;
    }

    @Info(value = "Configure this advancement's position",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display."),
            @Param(name = "modifyChildren", value = "Determine should its children apply the same offset.")
        })
    public AdvGetter displayOffset(float x, float y, boolean modifyChildren) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, modifyChildren));
        return this;
    }

    private void update() {
        GETTER_MAP.put(id, this);
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
