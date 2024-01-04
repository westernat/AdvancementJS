package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.BUILDER_MAP;
import static org.mesdag.advjs.adv.Data.DEFAULT_BACKGROUND;

public class AdvBuilder {
    @Nullable
    private final ResourceLocation parent;
    private final String name;
    private final ResourceLocation rootPath;
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
    private boolean sendsTelemetryEvent = false;

    private final boolean warn;

    public AdvBuilder(@Nullable ResourceLocation parent, String name, ResourceLocation rootPath, boolean warn) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.warn = warn;
    }

    @Info("Add a nameless child to this advancement, just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), UUID.randomUUID().toString(), rootPath, true);
        advBuilderConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), name, rootPath, false);
        advBuilderConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    @Info("Data related to the advancement's display.")
    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground(DEFAULT_BACKGROUND);
        }

        BUILDER_MAP.put(getSavePath(), this);
        return this;
    }

    @Info("The criteria to be tracked by this advancement.")
    public AdvBuilder criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);

        BUILDER_MAP.put(getSavePath(), this);
        return this;
    }

    @Info("The rewards provided when this advancement is obtained.")
    public AdvBuilder rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        rewardsBuilderConsumer.accept(rewardsBuilder);

        BUILDER_MAP.put(getSavePath(), this);
        return this;
    }

    @Info("Determines whether telemetry data should be collected when this advancement is achieved or not. Defaults to false.")
    public void sendsTelemetryEvent(boolean sendsTelemetryEvent) {
        this.sendsTelemetryEvent = sendsTelemetryEvent;
    }

    @Info("Get parent of this advancement.")
    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    @Info("Get the id of this advancement.")
    public ResourceLocation getSavePath() {
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @Info("Get the built display.")
    public DisplayInfo getDisplayInfo() {
        return displayBuilder.build();
    }

    @Info("Get the built criteria.")
    public Map<String, Criterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    @Info("Get the built requirements.")
    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    @Info("Get the built rewards.")
    public AdvancementRewards getRewards() {
        return rewardsBuilder.build();
    }

    public boolean isSendsTelemetryEvent() {
        return sendsTelemetryEvent;
    }

    @Info("If it is a root advancement.")
    public boolean isRoot() {
        return parent == null;
    }

    @Info("If it is a warn advancement.")
    public boolean isWarn() {
        return warn;
    }
}
