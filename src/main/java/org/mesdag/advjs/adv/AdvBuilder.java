package org.mesdag.advjs.adv;

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

    private final boolean warn;

    public AdvBuilder(@Nullable Identifier parent, String name, Identifier rootPath, boolean warn) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.warn = warn;
    }

    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), UUID.randomUUID().toString(), rootPath, true);
        advBuilderConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(getSavePath(), name, rootPath, false);
        advBuilderConsumer.accept(child);

        BUILDER_MAP.put(getSavePath(), this);
        return child;
    }

    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        displayBuilderConsumer.accept(displayBuilder);
        if (isRoot() && displayBuilder.getBackground() == null) {
            displayBuilder.setBackground(DEFAULT_BACKGROUND);
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

    public @Nullable Identifier getParent() {
        return parent;
    }

    public Identifier getSavePath() {
        return new Identifier(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    public AdvancementDisplay getDisplayInfo() {
        return displayBuilder.build();
    }

    public Map<String, AdvancementCriterion> getCriteria() {
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

    public boolean isWarn() {
        return warn;
    }
}
