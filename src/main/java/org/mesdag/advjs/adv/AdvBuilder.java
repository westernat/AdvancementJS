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

    private final boolean attention;

    public AdvBuilder(@Nullable ResourceLocation parent, String name, ResourceLocation rootPath, boolean attention) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.attention = attention;
    }

    @Info("Add a nameless child to this. Just for test.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advGetterConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), UUID.randomUUID().toString(), rootPath, true);
        advGetterConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    @Info("Add a named child to this.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advGetterConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), name, rootPath, false);
        advGetterConsumer.accept(child);

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

    public @Nullable ResourceLocation getParent() {
        return parent;
    }

    public ResourceLocation getSavePath() {
        return new ResourceLocation(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    public DisplayInfo getDisplayInfo() {
        return displayBuilder.build();
    }

    public Map<String, Criterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    public AdvancementRewards getRewards() {
        return rewardsBuilder.build();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isAttention() {
        return attention;
    }
}
