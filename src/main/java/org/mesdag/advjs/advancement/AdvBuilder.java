package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.DisplayOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.*;

public class AdvBuilder {
    private final @Nullable ResourceLocation parent;
    private final String name;
    private final ResourceLocation rootPath;
    private @Nullable DisplayBuilder displayBuilder;
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
    private boolean sendsTelemetryEvent = false;

    private WarnType warn;
    private final ResourceLocation id;
    private @Nullable DisplayInfo betterDisplayInfo;

    public AdvBuilder(@Nullable ResourceLocation parent, String name, ResourceLocation rootPath, WarnType warn) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.warn = warn;
        this.id = generateId();
        BUILDERS.put(id, this);
    }

    @Info("Add a nameless child to this advancement, just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> consumer) {
        AdvBuilder child = new AdvBuilder(id, UUID.randomUUID().toString(), rootPath, WarnType.NAMELESS);
        consumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> consumer) {
        AdvBuilder child = new AdvBuilder(id, name, rootPath, WarnType.NONE);
        consumer.accept(child);
        return child;
    }

    @Info("Data related to the advancement's display.")
    public AdvBuilder display(Consumer<DisplayBuilder> consumer) {
        DisplayBuilder builder = new DisplayBuilder(id);
        consumer.accept(builder);
        if (parent == null && builder.getBackground() == null) {
            builder.setBackground(new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"));
        }
        this.displayBuilder = builder;
        return this;
    }

    @Info("The criteria to be tracked by this advancement.")
    public AdvBuilder criteria(Consumer<CriteriaBuilder> consumer) {
        consumer.accept(criteriaBuilder);
        return this;
    }

    @Info("The rewards provided when this advancement is obtained.")
    public AdvBuilder rewards(Consumer<RewardsBuilder> consumer) {
        consumer.accept(rewardsBuilder);
        return this;
    }

    @Info("Determines whether telemetry data should be collected when this advancement is achieved or not. Defaults to false.")
    public AdvBuilder sendsTelemetryEvent() {
        this.sendsTelemetryEvent = true;
        return this;
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvBuilder requireParentDone() {
        if (parent == null) {
            ConsoleJS.SERVER.error("AdvJS/AdvBuilder#requireParentDone: Advancement '%s' is a root, so it can't check parent done".formatted(id));
            return this;
        }

        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<ResourceLocation> list = new ArrayList<>(Arrays.stream(REQUIRE_DONE.get(id)).toList());
            list.add(parent);
            REQUIRE_DONE.put(id, list.toArray(ResourceLocation[]::new));
        } else {
            REQUIRE_DONE.put(id, new ResourceLocation[]{parent});
        }
        return this;
    }

    @Info("It will check if advancements that you put in had done.")
    public AdvBuilder requireOthersDone(ResourceLocation... requires) {
        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<ResourceLocation> list = new ArrayList<>(Arrays.stream(requires).toList());
            list.add(parent);
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
    public AdvBuilder displayOffset(float x, float y) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, false));
        return this;
    }

    @Info(value = "Configure this advancement's position",
        params = {
            @Param(name = "offsetX", value = "The offset x of display."),
            @Param(name = "offsetY", value = "The offset y of display."),
            @Param(name = "modifyChildren", value = "Determine should its children apply the same offset.")
        })
    public AdvBuilder displayOffset(float x, float y, boolean modifyChildren) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, modifyChildren));
        return this;
    }

    @Info("""
        If invoked this method, the advancement will revoke after grant automatically.

        This is useful when you want to trigger it repeatedly.
        """)
    public AdvBuilder repeatable() {
        REPEATABLE.add(id);
        return this;
    }

    @Info("Get parent of this advancement.")
    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    private ResourceLocation generateId() {
        if (name.contains(":")) return new ResourceLocation(name);
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @Info("Get the id of this advancement.")
    public ResourceLocation getId() {
        return id;
    }

    @HideFromJS
    public @Nullable DisplayInfo getDisplayInfo() {
        return displayBuilder == null ? betterDisplayInfo : displayBuilder.build();
    }

    @HideFromJS
    public Map<String, Criterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    @HideFromJS
    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    @HideFromJS
    public AdvancementRewards getRewards() {
        return rewardsBuilder.build();
    }

    @HideFromJS
    public boolean isSendsTelemetryEvent() {
        return sendsTelemetryEvent;
    }

    @HideFromJS
    public WarnType getWarn() {
        return warn;
    }

    @HideFromJS
    public AdvBuilder setWarn(WarnType warn) {
        this.warn = warn;
        return this;
    }

    @HideFromJS
    public void setBetterDisplayInfo(@Nullable DisplayInfo displayInfo) {
        this.betterDisplayInfo = displayInfo;
        this.displayBuilder = null;
    }

    public enum WarnType {
        NONE(Component.empty()),
        NAMELESS(Component.translatable("advjs.attention.nameless")),
        NO_SPACE(Component.translatable("advjs.attention.no_space")),
        NO_PARENT(Component.translatable("advjs.attention.no_parent"));

        public final Component msg;

        WarnType(Component msg) {
            this.msg = msg;
        }
    }
}
