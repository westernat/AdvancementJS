package org.mesdag.advjs.adv;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.BUILDER_MAP;
import static org.mesdag.advjs.adv.Data.DEFAULT_BACKGROUND;

public class AdvBuilder {
    @Nullable
    private final Identifier parent;
    private final String name;
    private final Identifier rootPath;
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
    private boolean sendsTelemetryEvent = false;

    private final boolean warn;

    @HideFromJS
    public AdvBuilder(@Nullable Identifier parent, String name, Identifier rootPath, boolean warn) {
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
    public @Nullable Identifier getParent() {
        return parent;
    }

    @Info("Get the id of this advancement.")
    public Identifier getSavePath() {
        return new Identifier(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @HideFromJS
    public AdvancementDisplay getDisplayInfo() {
        return displayBuilder.build();
    }

    @HideFromJS
    public Map<String, AdvancementCriterion> getCriteria() {
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

    @Info("If it is a root advancement.")
    public boolean isRoot() {
        return parent == null;
    }

    @Info("If it is a warn advancement.")
    public boolean isWarn() {
        return warn;
    }
}
