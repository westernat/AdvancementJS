package org.mesdag.advjs.adv;

import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.adv.Data.BUILDER_MAP;

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

    public AdvBuilder addChild(Consumer<AdvBuilder> advGetterConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), UUID.randomUUID().toString(), rootPath, true);
        advGetterConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advGetterConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), name, rootPath, false);
        advGetterConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground("textures/gui/advancements/backgrounds/stone.png");
        }

        BUILDER_MAP.put(getSavePath(), this);
        return this;
    }

    public AdvBuilder criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);

        BUILDER_MAP.put(getSavePath(), this);
        return this;
    }

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
