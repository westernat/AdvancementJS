package org.mesdag.advjs.configure;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;

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
    private final DisplayBuilder displayBuilder = new DisplayBuilder();
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();

    private final boolean warn;

    @HideFromJS
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
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), name, rootPath, false);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Data related to the advancement's display.")
    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground(DEFAULT_BACKGROUND);
        }
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

    @Info("It will check if parent done. Defaults do not check.")
    public AdvBuilder requireParentDone() {
        REQUIRE_DONE.add(getSavePath());
        return this;
    }

    private void update() {
        BUILDER_MAP.put(getSavePath(), this);
    }

    @Info("Get parent id.")
    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    @Info("Get the id.")
    public ResourceLocation getSavePath() {
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @HideFromJS
    public DisplayInfo getDisplayInfo() {
        return displayBuilder.build();
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

    @Info("If it is a root advancement.")
    public boolean isRoot() {
        return parent == null;
    }

    @Info("If it is a warn advancement.")
    public boolean isWarn() {
        return warn;
    }
}
