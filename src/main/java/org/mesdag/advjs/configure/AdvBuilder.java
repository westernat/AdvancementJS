package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.util.DisplayOffset;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.configure.Data.*;

public class AdvBuilder {
    @Nullable
    private final ResourceLocation parent;
    private final String name;
    private final ResourceLocation rootPath;
    @Nullable
    private DisplayBuilder displayBuilder;
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
    private boolean sendsTelemetryEvent = false;

    private WarnType warn;
    private final ResourceLocation id;

    @HideFromJS
    public AdvBuilder(@Nullable ResourceLocation parent, String name, ResourceLocation rootPath, WarnType warn) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.warn = warn;
        this.id = getSavePath();
    }

    @Info("Add a nameless child to this advancement, just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, UUID.randomUUID().toString(), rootPath, WarnType.NAMELESS);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, name, rootPath, WarnType.NONE);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Data related to the advancement's display.")
    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        DisplayBuilder builder = new DisplayBuilder(id);
        displayBuilderConsumer.accept(builder);
        if (parent == null && builder.getBackground() == null) {
            builder.setBackground(new ResourceLocation("textures/gui/advancements/backgrounds/stone.png"));
        }
        this.displayBuilder = builder;
        update();
        return this;
    }

    @Info("The criteria to be tracked by this advancement.")
    public AdvBuilder criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);
        update();
        return this;
    }

    @Info("The rewards provided when this advancement is obtained.")
    public AdvBuilder rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        rewardsBuilderConsumer.accept(rewardsBuilder);
        update();
        return this;
    }

    @Info("Determines whether telemetry data should be collected when this advancement is achieved or not. Defaults to false.")
    public AdvBuilder sendsTelemetryEvent() {
        this.sendsTelemetryEvent = true;
        update();
        return this;
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvBuilder requireParentDone() {
        REQUIRE_DONE.add(id);
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

    private void update() {
        BUILDER_MAP.put(id, this);
    }

    @Info("Get parent of this advancement.")
    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    private ResourceLocation getSavePath() {
        if (name.contains(":")) {
            return new ResourceLocation(name);
        }
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @Info("Get the id of this advancement.")
    public ResourceLocation getId() {
        return id;
    }

    @HideFromJS
    public DisplayInfo getDisplayInfo() {
        return displayBuilder == null ? null : displayBuilder.build();
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
    public void setWarn(WarnType warn) {
        this.warn = warn;
    }

    public enum WarnType {
        NONE(Component.empty()),
        NAMELESS(Component.translatable("advjs.attention.nameless"));

        public final Component msg;

        WarnType(Component msg) {
            this.msg = msg;
        }
    }
}
