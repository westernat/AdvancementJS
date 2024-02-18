package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.util.DisplayOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.*;

public class AdvGetter {
    private final ResourceLocation id;
    public @Nullable ResourceLocation parent;
    @HideFromJS
    public @Nullable Consumer<DisplayBuilder> displayConsumer;
    @HideFromJS
    public Consumer<RewardsBuilder> rewardsConsumer = rewardsBuilder -> {
    };
    @HideFromJS
    public Consumer<CriteriaBuilder> criteriaConsumer = criteriaBuilder -> {
    };

    public AdvGetter(ResourceLocation id) {
        this.id = id;
        GETTERS.put(id, this);
    }

    @Info("Change the parent. Defaults to original parent.")
    public AdvGetter changeParent(ResourceLocation parentId) {
        this.parent = parentId;
        return this;
    }

    @Info("Modify the display. Defaults to original display.")
    public AdvGetter modifyDisplay(Consumer<DisplayBuilder> displayBuilderConsumer) {
        this.displayConsumer = displayBuilderConsumer;
        return this;
    }

    @Info("Modify the criteria. Defaults to original criteria.")
    public AdvGetter modifyCriteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        this.criteriaConsumer = criteriaBuilderConsumer;
        return this;
    }

    @Info("Modify the rewards. Defaults to original rewards.")
    public AdvGetter modifyRewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        this.rewardsConsumer = rewardsBuilderConsumer;
        return this;
    }

    @Info("Add a nameless child to this advancement just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, UUID.randomUUID().toString(), getRootPath(id), AdvBuilder.WarnType.NAMELESS);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, name, getRootPath(id), AdvBuilder.WarnType.NONE);
        advBuilderConsumer.accept(child);
        return child;
    }

    private static ResourceLocation getRootPath(ResourceLocation savePath) {
        String[] paths = savePath.getPath().split("/");
        String path = paths.length == 1 ? paths[0] : String.join("/", Arrays.copyOfRange(paths, 0, paths.length - 1));
        return new ResourceLocation(savePath.getNamespace(), path);
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvGetter requireParentDone() {
        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<ResourceLocation> list = new ArrayList<>(Arrays.stream(REQUIRE_DONE.get(id)).toList());
            list.add(AdvJS.PARENT);
            REQUIRE_DONE.put(id, list.toArray(ResourceLocation[]::new));
        } else {
            REQUIRE_DONE.put(id, new ResourceLocation[]{AdvJS.PARENT});
        }
        return this;
    }

    @Info("It will check if advancements that you put in had done.")
    public AdvGetter requireOthersDone(ResourceLocation... requires) {
        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<ResourceLocation> list = new ArrayList<>(Arrays.stream(requires).toList());
            list.add(AdvJS.PARENT);
            REQUIRE_DONE.put(id, list.toArray(ResourceLocation[]::new));
        } else {
            REQUIRE_DONE.put(id, requires);
        }
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

    @Info("""
        If invoked this method, the advancement will revoke after grant automatically.

        This is useful when you want to trigger it repeatedly.
        """)
    public AdvGetter repeatable() {
        REPEATABLE.add(id);
        return this;
    }
}
